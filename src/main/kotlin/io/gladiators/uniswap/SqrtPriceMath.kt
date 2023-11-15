package io.gladiators.uniswap

import java.math.BigInteger

object SqrtPriceMath {
    fun getAmount0Delta(
        sqrtRatioAX96: BigInteger,
        sqrtRatioBX96: BigInteger,
        liquidity: BigInteger
    ): BigInteger {
        return if (liquidity < BigInteger.ZERO) getAmount0Delta(
            sqrtRatioAX96,
            sqrtRatioBX96,
            liquidity.negate(),
            false
        ).negate() else getAmount0Delta(sqrtRatioAX96, sqrtRatioBX96, liquidity, true)
    }

    fun getAmount0Delta(
        sqrtRatioAX96: BigInteger,
        sqrtRatioBX96: BigInteger,
        liquidity: BigInteger,
        roundUp: Boolean
    ): BigInteger {
        val (sqrtAX96, sqrtBX96) = if (sqrtRatioAX96 > sqrtRatioBX96) sqrtRatioBX96 to sqrtRatioAX96 else sqrtRatioAX96 to sqrtRatioBX96
        val numerator1 = liquidity.shiftLeft(FixedPoint96.RESOLUTION)
        val numerator2 = sqrtRatioBX96.subtract(sqrtAX96)
        assert(sqrtAX96 > BigInteger.ZERO)
        return if (roundUp) UnsafeMath.divRoundingUp(
            FullMath.mulDivRoundingUp(numerator1, numerator2, sqrtBX96),
            sqrtAX96
        ) else FullMath.mulDiv(numerator1, numerator2, sqrtBX96).divide(sqrtAX96)
    }

    fun getAmount1Delta(
        sqrtRatioAX96: BigInteger,
        sqrtRatioBX96: BigInteger,
        liquidity: BigInteger
    ): BigInteger {
        return if (liquidity < BigInteger.ZERO) getAmount1Delta(
            sqrtRatioAX96,
            sqrtRatioBX96,
            liquidity.negate(),
            false
        ).negate() else getAmount1Delta(sqrtRatioAX96, sqrtRatioBX96, liquidity, true)
    }

    fun getAmount1Delta(
        sqrtRatioAX96: BigInteger,
        sqrtRatioBX96: BigInteger,
        liquidity: BigInteger,
        roundUp: Boolean
    ): BigInteger {
        val (sqrtAX96, sqrtBX96) = if (sqrtRatioAX96 > sqrtRatioBX96) sqrtRatioBX96 to sqrtRatioAX96 else sqrtRatioAX96 to sqrtRatioBX96
        return if (roundUp) FullMath.mulDivRoundingUp(
            liquidity,
            sqrtBX96.subtract(sqrtAX96),
            FixedPoint96.Q96
        ) else FullMath.mulDiv(liquidity, sqrtBX96.subtract(sqrtAX96), FixedPoint96.Q96)
    }

    private fun getNextSqrtPriceFromAmount0RoundingUp(
        sqrtPX96: BigInteger,
        liquidity: BigInteger,
        amount: BigInteger,
        add: Boolean
    ): BigInteger {
        // we short circuit amount == 0 because the result is otherwise not guaranteed to equal the input price
        if (amount == BigInteger.ZERO) {
            return sqrtPX96
        }
        val numerator1 = liquidity.shiftLeft(FixedPoint96.RESOLUTION)
        return if (add) {
            val product = amount.multiply(sqrtPX96)
            if (product.divide(amount) == sqrtPX96) {
                val denominator = numerator1.add(product)
                if (denominator >= numerator1) {
                    // always fits in 160 bits
                    return FullMath.mulDivRoundingUp(numerator1, sqrtPX96, denominator)
                }
            }
            UnsafeMath.divRoundingUp(numerator1, numerator1.divide(sqrtPX96).add(amount))
        } else {
            // if the product overflows, we know the denominator underflows
            // in addition, we must check that the denominator does not underflow
            val product = amount.multiply(sqrtPX96)
            assert(product.divide(amount) == sqrtPX96 && numerator1 > product)
            val denominator = numerator1.subtract(product)
            FullMath.mulDivRoundingUp(numerator1, sqrtPX96, denominator)
        }
    }

    private fun getNextSqrtPriceFromAmount1RoundingDown(
        sqrtPX96: BigInteger,
        liquidity: BigInteger,
        amount: BigInteger,
        add: Boolean
    ): BigInteger {
        return if (add) {
            val quotient = if (amount <= IntUtils.MAX_UINT160) amount.shiftLeft(FixedPoint96.RESOLUTION)
                .divide(liquidity) else FullMath.mulDiv(amount, FixedPoint96.Q96, liquidity)
            sqrtPX96.add(quotient)
        } else {
            val quotient = if (amount <= IntUtils.MAX_UINT160) UnsafeMath.divRoundingUp(
                amount.shiftLeft(FixedPoint96.RESOLUTION), liquidity
            ) else FullMath.mulDivRoundingUp(amount, FixedPoint96.Q96, liquidity)
            assert(sqrtPX96 > quotient)
            // always fits 160 bits
            sqrtPX96.subtract(quotient)
        }
    }

    fun getNextSqrtPriceFromInput(
        sqrtPX96: BigInteger,
        liquidity: BigInteger,
        amountIn: BigInteger,
        zeroForOne: Boolean
    ): BigInteger {
        assert(sqrtPX96 > BigInteger.ZERO)
        assert(liquidity > BigInteger.ZERO)

        // round to make sure that we don't pass the target price
        return if (zeroForOne) getNextSqrtPriceFromAmount0RoundingUp(
            sqrtPX96,
            liquidity,
            amountIn,
            true
        ) else getNextSqrtPriceFromAmount1RoundingDown(sqrtPX96, liquidity, amountIn, true)
    }

    fun getNextSqrtPriceFromOutput(
        sqrtPX96: BigInteger,
        liquidity: BigInteger,
        amountOut: BigInteger,
        zeroForOne: Boolean
    ): BigInteger {
        assert(sqrtPX96 > BigInteger.ZERO)
        assert(liquidity > BigInteger.ZERO)

        // round to make sure that we pass the target price
        return if (zeroForOne) getNextSqrtPriceFromAmount1RoundingDown(
            sqrtPX96,
            liquidity,
            amountOut,
            false
        ) else getNextSqrtPriceFromAmount0RoundingUp(sqrtPX96, liquidity, amountOut, false)
    }
}
