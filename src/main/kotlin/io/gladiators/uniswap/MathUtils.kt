package io.gladiators.uniswap

import java.math.BigDecimal
import java.math.BigInteger

object MathUtils {
    fun lt(x: BigInteger, y: BigInteger?): BigInteger {
        return if (x < y) BigInteger.ONE else BigInteger.ZERO
    }

    fun gt(x: BigInteger, y: BigInteger?): BigInteger {
        return if (x > y) BigInteger.ONE else BigInteger.ZERO
    }

    fun pow(base: BigInteger, exponent: Int): BigInteger {
        return (0 until exponent).fold(BigInteger.ONE) { acc, _ -> acc.multiply(base) }
    }

    fun pow(base: BigDecimal, exponent: Int): BigDecimal {
        return (0 until exponent).fold(BigDecimal.ONE) { acc, _ -> acc.multiply(base) }
    }

    fun pow10(exponent: Int): BigInteger {
        return pow(BigInteger.TEN, exponent)
    }

    fun pow10Decimal(exponent: Int): BigDecimal {
        return pow(BigDecimal.TEN, exponent)
    }

    fun min(a: BigInteger, b: BigInteger): BigInteger {
        return if (a > b) b else a
    }

    fun max(a: BigInteger, b: BigInteger): BigInteger {
        return if (a > b) a else b
    }

    fun sum(array: Array<BigInteger>): BigInteger {
        var result = BigInteger.ZERO
        for (cur in array) {
            result = result.add(cur)
        }
        return result
    }
}
