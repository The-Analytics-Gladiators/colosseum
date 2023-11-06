package io.gladiators

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toCollection
import kotlin.time.Duration.Companion.milliseconds

class CoroutinesKtTest : FunSpec({

    timeout = 200.milliseconds.inWholeMilliseconds

    test("Batched flow") {
        val collector = mutableListOf<List<Int>>()
        listOf(1,2,3).asFlow().batched(2).toCollection(collector)
        collector shouldBe listOf(listOf(1,2), listOf(3))
        collector.clear()
        listOf(1,2,3).asFlow().batched(3).toCollection(collector)
        collector shouldBe listOf(listOf(1,2, 3))
        collector.clear()
        listOf(1,2,3).asFlow().batched(4).toCollection(collector)
        collector shouldBe listOf(listOf(1,2, 3))
        collector.clear()
        listOf(1,2,3).asFlow().batched(1).toCollection(collector)
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
