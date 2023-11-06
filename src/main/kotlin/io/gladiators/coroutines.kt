package io.gladiators

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.whileSelect
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicReference
import kotlin.time.Duration

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.timeWindow(interval: Duration, size: Int): Flow<List<T>> = channelFlow {
    val queue = ArrayBlockingQueue<T>(size)

    val coroutine = launch {
        collect { value ->
            queue.offer(value)
        }
    }
    val producerScope = this
    whileSelect {
        onTimeout(interval) {
            val batch = mutableListOf<T>()
            queue.drainTo(batch)
            if(batch.isNotEmpty() && producerScope.isActive) {
                channel.send(batch)
            }
            val isContinue = coroutine.isActive && producerScope.isActive
            isContinue
        }
    }
    close()
}

@OptIn(DelicateCoroutinesApi::class)
fun <T> Flow<T>.batched(size: Int): Flow<List<T>> = channelFlow<List<T>> {
    val queue = ArrayBlockingQueue<T>(size)
    launch {
        collect { value ->
            queue.offer(value)
            if(queue.size >= size && !channel.isClosedForSend) {
                val batch = mutableListOf<T>()
                queue.drainTo(batch)
                channel.send(batch)
            }
        }
        if(queue.isNotEmpty() && !channel.isClosedForSend) {
            val batch = mutableListOf<T>()
            queue.drainTo(batch)
            channel.send(batch)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun <T, V> Flow<T>.grouped(f: (T) -> V): Flow<List<T>> = channelFlow {
    val currentElement: AtomicReference<V?> = AtomicReference(null)
    val queue = CopyOnWriteArrayList<T>()
    launch {
        collect { value ->
            if (currentElement.get() == f(value)) {
                queue.add(value)
            } else {
                if(!channel.isClosedForSend) {
                    val batch = mutableListOf<T>()
                    batch.addAll(queue)
                    queue.clear()
                    queue.add(value)
                    currentElement.set(f(value))
                    if (batch.isNotEmpty()) channel.send(batch)
                }
            }
        }
        if(queue.isNotEmpty() && !channel.isClosedForSend) {
            val batch = mutableListOf<T>()
            batch.addAll(queue)
            queue.clear()
            channel.send(batch)
        }
    }
}
