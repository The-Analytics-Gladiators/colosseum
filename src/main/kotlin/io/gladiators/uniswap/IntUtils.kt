package io.gladiators.uniswap

import java.math.BigInteger

object IntUtils {
    val MAX_UINT8 = BigInteger("ff", 16)
    val MAX_UINT16 = BigInteger("ffff", 16)
    val MAX_UINT32 = BigInteger("ffffffff", 16)
    val MAX_UINT64 = BigInteger("ffffffffffffffff", 16)
    val MAX_UINT96 = BigInteger("ffffffffffffffffffffffff", 16)
    val MAX_UINT128 = BigInteger("ffffffffffffffffffffffffffffffff", 16)
    val MAX_UINT160 = BigInteger("ffffffffffffffffffffffffffffffffffffffff", 16)
    val MAX_INT256 = BigInteger("7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16)
    val MAX_UINT256 = BigInteger("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16)
    val TWO_POW_96 = MAX_UINT96.add(BigInteger.ONE)
    val TWO_POW_128 = MAX_UINT128.add(BigInteger.ONE)
    val TWO_POW_160 = MAX_UINT160.add(BigInteger.ONE)
    val TWO_POW_256 = MAX_UINT256.add(BigInteger.ONE)
    fun uint128(n: BigInteger): BigInteger {
        return if (n < BigInteger.ZERO) {
            n.add(TWO_POW_128)
        } else n.mod(TWO_POW_128)
    }

    fun uint256(n: BigInteger): BigInteger {
        return if (n < BigInteger.ZERO) {
            n.add(TWO_POW_256)
        } else n.mod(TWO_POW_256)
    }

    fun uint96(n: BigInteger): BigInteger {
        return if (n < BigInteger.ZERO) {
            n.add(TWO_POW_96)
        } else n.mod(TWO_POW_96)
    }

    fun uint8(i: Int): Int {
        return if (i < 0) i + 256 else i
    }
}
