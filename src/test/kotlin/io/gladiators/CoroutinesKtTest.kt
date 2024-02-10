package io.gladiators

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toCollection
import kotlin.time.Duration.Companion.milliseconds

class CoroutinesKtTest : FunSpec({

    timeout = 200.milliseconds.inWholeMilliseconds

    test("chunked flow") {
        val collector = mutableListOf<List<Int>>()
        listOf(1, 2, 3).asFlow().chunkedFlow(10.milliseconds).toCollection(collector)
        collector shouldBe listOf(listOf(1, 2, 3))
    }

    test("chunked flow chunk starts with the first element") {
        val collector = mutableListOf<List<Int>>()
        flow {
            emit(1)
            kotlinx.coroutines.delay(20.milliseconds)
            emit(2)
            emit(3)
        }.chunkedFlow(10.milliseconds).toCollection(collector)
        collector shouldBe listOf(listOf(1), listOf(2, 3))
    }

    test("chunked flow accumulates values that have small delays") {
        val collector = mutableListOf<List<Int>>()
        flow {
            emit(1)
            kotlinx.coroutines.delay(3.milliseconds)
            emit(2)
            kotlinx.coroutines.delay(3.milliseconds)
            emit(3)
        }.chunkedFlow(10.milliseconds).toCollection(collector)
        collector shouldBe listOf(listOf(1, 2, 3))
    }
    test("Batched flow") {
        val collector = mutableListOf<List<Int>>()
        listOf(1, 2, 3).asFlow().batched(2).toCollection(collector)
        collector shouldBe listOf(listOf(1, 2), listOf(3))
        collector.clear()
        listOf(1, 2, 3).asFlow().batched(3).toCollection(collector)
        collector shouldBe listOf(listOf(1, 2, 3))
        collector.clear()
        listOf(1, 2, 3).asFlow().batched(4).toCollection(collector)
        collector shouldBe listOf(listOf(1, 2, 3))
        collector.clear()
        listOf(1, 2, 3).asFlow().batched(1).toCollection(collector)
        collector shouldBe listOf(listOf(1), listOf(2), listOf(3))
    }

    test("Grouped flow") {
        val collector = mutableListOf<List<Int>>()
        listOf(1, 1, 2, 2, 2, 3).asFlow().grouped { it }.toCollection(collector)
        collector shouldBe listOf(listOf(1, 1), listOf(2, 2, 2), listOf(3))
        collector.clear()

        listOf(1, 2, 1, 2).asFlow().grouped { it }.toCollection(collector)
        collector shouldBe listOf(listOf(1), listOf(2), listOf(1), listOf(2))
    }
})
