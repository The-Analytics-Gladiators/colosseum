package io.gladiators.uniswap

import java.math.BigInteger

object SwapMath {
    fun computeSwapStep(
        sqrtRatioCurrentX96: BigInteger,
        sqrtRatioTargetX96: BigInteger,
        liquidity: BigInteger,
        amountRemaining: BigInteger,
        feePips: Int
    ): ComputeSwapStepResult {
        val zeroForOne = sqrtRatioCurrentX96 >= sqrtRatioTargetX96
        val exactIn = amountRemaining >= BigInteger.ZERO
        val tenE6 = BigInteger.valueOf(1000000)
        val sqrtRatioNextX96: BigInteger
        var amountIn = BigInteger.ZERO
        var amountOut = BigInteger.ZERO
        if (exactIn) {
            val amountRemainingLessFee: BigInteger =
                FullMath.mulDiv(amountRemaining, tenE6.subtract(BigInteger.valueOf(feePips.toLong())), tenE6)
            amountIn = if (zeroForOne) SqrtPriceMath.getAmount0Delta(
                sqrtRatioTargetX96,
                sqrtRatioCurrentX96,
                liquidity,
                true
            ) else SqrtPriceMath.getAmount1Delta(sqrtRatioCurrentX96, sqrtRatioTargetX96, liquidity, true)
            sqrtRatioNextX96 = if (amountRemainingLessFee >= amountIn) {
                sqrtRatioTargetX96
            } else {
                SqrtPriceMath.getNextSqrtPriceFromInput(
                    sqrtRatioCurrentX96,
                    liquidity,
                    amountRemainingLessFee,
                    zeroForOne
                )
            }
        } else {
            amountOut = if (zeroForOne) SqrtPriceMath.getAmount1Delta(
                sqrtRatioTargetX96,
                sqrtRatioCurrentX96,
                liquidity,
                false
            ) else SqrtPriceMath.getAmount0Delta(sqrtRatioCurrentX96, sqrtRatioTargetX96, liquidity, false)
            sqrtRatioNextX96 = if (uint256(amountRemaining.negate()) >= amountOut) {
                sqrtRatioTargetX96
            } else {
                SqrtPriceMath.getNextSqrtPriceFromOutput(
                    sqrtRatioCurrentX96,
                    liquidity,
                    uint256(amountRemaining.negate()),
                    zeroForOne
                )
            }
        }
        val max = sqrtRatioTargetX96 == sqrtRatioNextX96

        if (zeroForOne) {
            amountIn = if (max && exactIn) amountIn else SqrtPriceMath.getAmount0Delta(
                sqrtRatioNextX96,
                sqrtRatioCurrentX96,
                liquidity,
                true
            )
            amountOut = if (max && !exactIn) amountOut else SqrtPriceMath.getAmount1Delta(
                sqrtRatioNextX96,
                sqrtRatioCurrentX96,
                liquidity,
                false
            )
        } else {
            amountIn = if (max && exactIn) amountIn else SqrtPriceMath.getAmount1Delta(
                sqrtRatioCurrentX96,
                sqrtRatioNextX96,
                liquidity,
                true
            )
            amountOut = if (max && !exactIn) amountOut else SqrtPriceMath.getAmount0Delta(
                sqrtRatioCurrentX96,
                sqrtRatioNextX96,
                liquidity,
                false
            )
        }

        if (!exactIn && amountOut > uint256(amountRemaining.negate())) {
            amountOut = uint256(amountRemaining.negate())
        }
        val feeAmount: BigInteger = if (exactIn && sqrtRatioNextX96 !== sqrtRatioTargetX96) {
            uint256(amountRemaining).subtract(amountIn)
        } else {
            FullMath.mulDivRoundingUp(
                amountIn,
                BigInteger.valueOf(feePips.toLong()),
                tenE6.subtract(BigInteger.valueOf(feePips.toLong()))
            )
        }
        return ComputeSwapStepResult(sqrtRatioNextX96, amountIn, amountOut, feeAmount)
    }

    private fun uint256(n: BigInteger): BigInteger {
        return if (n < BigInteger.ZERO) {
            n.add(IntUtils.MAX_UINT256)
        } else n
    }

    class ComputeSwapStepResult(
        var sqrtRatioNextX96: BigInteger,
        var amountIn: BigInteger,
        var amountOut: BigInteger,
        var feeAmount: BigInteger
    )
}
