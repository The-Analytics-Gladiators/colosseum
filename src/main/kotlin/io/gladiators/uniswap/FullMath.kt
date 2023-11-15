package io.gladiators.uniswap

import java.math.BigInteger

object FullMath {
    fun mulDivRoundingUp(a: BigInteger, b: BigInteger, denominator: BigInteger): BigInteger {
        var result = mulDiv(a, b, denominator)
        if (mulmod(a, b, denominator) > BigInteger.ZERO) {
            assert(result < IntUtils.MAX_UINT256)
            result = result.add(BigInteger.ONE)
        }
        return result
    }

    private fun mulmod(x: BigInteger, y: BigInteger, m: BigInteger): BigInteger {
        return x.multiply(y).mod(m)
    }

    fun mulDiv(a: BigInteger, b: BigInteger, denominator: BigInteger): BigInteger {
        return a.multiply(b).divide(denominator)
    }
}
