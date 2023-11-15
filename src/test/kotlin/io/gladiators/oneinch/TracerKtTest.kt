package io.gladiators.oneinch

import io.gladiators.web3.Chain
import io.kotest.core.spec.style.FunSpec
import io.kotest.engine.test.logging.trace
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class TracerKtTest : FunSpec({

    test("tracer smoketest") {
        withOneInchTracer(Chain.BSC) {
            val trace = txTrace(33522898.toBigInteger(), "0x754144a308a69447a4cd90f9527773da1d9bf3ad7a78c441dbff8d4691b8e2c7")
            trace.from shouldNotBe ""
            trace.to shouldNotBe ""
            trace.calls shouldHaveSize 5
        }

    }

})
