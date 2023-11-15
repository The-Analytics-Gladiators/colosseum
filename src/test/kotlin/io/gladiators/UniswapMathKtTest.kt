package io.gladiators

import io.gladiators.uniswap.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import java.math.BigInteger

class UniswapMathKtTest : StringSpec({
    "price turnaround" {
        val tick = tickForPrice(100.0)
        val price = priceForTick(tick)
        price.toDouble() shouldBeLessThan 100.001
        price.toDouble() shouldBeGreaterThan 99.001
    }

    "sqrt price turnaround" {
        val sqrt = priceToSqrtPrice(100.toBigInteger())
        val price = sqrtPriceX96ToPrice(sqrt).toDouble()
        price shouldBeLessThan 100.001
        price shouldBeGreaterThan 99.001
    }

    "liquidity for amounts" {
        getLiquidityForAmounts(
            amount0 = BigInteger("5000000000000000000"),
            amount1 = BigInteger("3404601"),
            sqrtRatioX96 = BigInteger("65393762491167726846326"),
            sqrtRatioAX96 = BigInteger("65368648264591996512818"),
            sqrtRatioBX96 = BigInteger("65401339126242838137898")
        ) shouldBe BigInteger("10740537062164443")
    }

    "amount0ForLiquidity" {
        getAmount0ForLiquidity(
            sqrtRatioAX96 = BigInteger("65368648264591996512818"),
            sqrtRatioBX96 = BigInteger("65401339126242838137898"),
            liquidity = BigInteger("10740537062164443")
        ) shouldBe BigInteger("6506925048062242142")
    }

    "amount1ForLiquidity" {
        getAmount1ForLiquidity(
            sqrtRatioAX96 = BigInteger("65368648264591996512818"),
            sqrtRatioBX96 = BigInteger("65401339126242838137898"),
            liquidity = BigInteger("10740537062164443")
        ) shouldBe BigInteger("4431724")
    }

    "fee for V2 Pool" {
        val fee = feeForV2Pool(
            reserve0 = BigInteger("1000000"),
            reserve1 = BigInteger("1000000"),
            amount0In = BigInteger("10000"),
            amount1Out = BigInteger("9881")
        )

        fee shouldBe 2000
    }
})
