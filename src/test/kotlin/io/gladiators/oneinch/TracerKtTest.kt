package io.gladiators.oneinch

import io.github.resilience4j.kotlin.ratelimiter.RateLimiterRegistry
import io.github.resilience4j.kotlin.ratelimiter.executeFunction
import io.github.resilience4j.kotlin.ratelimiter.withRateLimiterConfig
import io.gladiators.web3.Chain
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldNotBe
import io.kotest.property.forAll
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

class TracerKtTest : FunSpec({

    threads = 2

    val rateLimiterRegistry = RateLimiterRegistry {
        withRateLimiterConfig {
            limitForPeriod(1)
            limitRefreshPeriod(1.4.seconds.toJavaDuration())
            timeoutDuration(5.seconds.toJavaDuration())
        }
    }

    val rateLimiter = rateLimiterRegistry.rateLimiter("oneinch")

    context("tracer smoke") {
        withOneInch(Chain.BSC) {
            withData(
                33522898.toBigInteger() to "0x754144a308a69447a4cd90f9527773da1d9bf3ad7a78c441dbff8d4691b8e2c7",
                33578009.toBigInteger() to "0xd6b53a04d6dc0e8eb2a9fb63b474211ae6660f31fc1cb4775733552948d0c3d6",
                33577578.toBigInteger() to "0xfd4600ba9f7d41f5f51e156c03b8b82fb72de7e9171d049bcb54e12f1ccb1c54"
            ) { (block, hash) ->
                val trace = rateLimiter.executeFunction { txTrace(block, hash) }
                trace.from shouldNotBe ""
                trace.to shouldNotBe ""
                trace.calls shouldHaveAtLeastSize 5
            }
        }
    }

})
