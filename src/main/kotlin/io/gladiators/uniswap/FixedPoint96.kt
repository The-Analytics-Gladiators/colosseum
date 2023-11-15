package io.gladiators.uniswap

import java.math.BigInteger

object FixedPoint96 {
    const val RESOLUTION = 96
    val Q96 = BigInteger("1000000000000000000000000", 16)
}
