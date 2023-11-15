package io.gladiators.uniswap

import java.math.BigInteger

object UnsafeMath {
    fun divRoundingUp(x: BigInteger, y: BigInteger?): BigInteger {
        return x.divide(y).add(MathUtils.gt(x.mod(y), BigInteger.ZERO))
    }
}
