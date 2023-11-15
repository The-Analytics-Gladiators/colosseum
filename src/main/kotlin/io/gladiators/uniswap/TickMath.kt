package io.gladiators.uniswap

import io.gladiators.uniswap.MathUtils.gt
import java.math.BigInteger

object TickMath {
    const val MIN_TICK = -887272
    const val MAX_TICK = -MIN_TICK

    val MIN_SQRT_RATIO = BigInteger("4295128739")

    val MAX_SQRT_RATIO = BigInteger("1461446703485210103287273052203988822378723970342")
    fun getSqrtRatioAtTick(tick: Int): BigInteger {
        val absTick = BigInteger.valueOf(tick.toLong()).abs()
        check(absTick <= BigInteger.valueOf(MAX_TICK.toLong())) { "getSqrtRatioAtTick: $tick tick can't be superior to MAX_TICK" }
        var ratio = if (absTick.and(BigInteger.valueOf(0x1)) != BigInteger.ZERO) BigInteger(
            "fffcb933bd6fad37aa2d162d1a594001",
            16
        ) else BigInteger("100000000000000000000000000000000", 16)
        if (absTick.and(BigInteger.valueOf(0x2)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("fff97272373d413259a46990580e213a", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x4)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("fff2e50f5f656932ef12357cf3c7fdcc", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x8)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("ffe5caca7e10e4e61c3624eaa0941cd0", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x10)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("ffcb9843d60f6159c9db58835c926644", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x20)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("ff973b41fa98c081472e6896dfb254c0", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x40)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("ff2ea16466c96a3843ec78b326b52861", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x80)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("fe5dee046a99a2a811c461f1969c3053", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x100)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("fcbe86c7900a88aedcffc83b479aa3a4", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x200)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("f987a7253ac413176f2b074cf7815e54", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x400)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("f3392b0822b70005940c7a398e4b70f3", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x800)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("e7159475a2c29b7443b29c7fa6e889d9", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x1000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("d097f3bdfd2022b8845ad8f792aa5825", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x2000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("a9f746462d870fdf8a65dc1f90e061e5", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x4000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("70d869a156d2a1b890bb3df62baf32f7", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x8000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("31be135f97d08fd981231505542fcfa6", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x10000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("9aa508b5b7a84e1c677de54f3e99bc9", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x20000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("5d6af8dedb81196699c329225ee604", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x40000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("2216e584f5fa1ea926041bedfe98", 16))
                .shiftRight(128)
        if (absTick.and(BigInteger.valueOf(0x80000)) != BigInteger.ZERO) ratio =
            ratio.multiply(BigInteger("48a170391f7dc42444e8fa2", 16))
                .shiftRight(128)
        if (tick > 0) {
            ratio = IntUtils.MAX_UINT256.divide(ratio)
        }

        return ratio.shiftRight(32)
            .add(if (ratio.mod(BigInteger.ONE.shiftLeft(32)) == BigInteger.ZERO) BigInteger.ZERO else BigInteger.ONE)
    }

    fun getTickAtSqrtRatio(sqrtPriceX96: BigInteger): Int {
        // second inequality must be < because the price can never reach the price at the max tick
        check(
            !(sqrtPriceX96.compareTo(MIN_SQRT_RATIO) < 0
                    && sqrtPriceX96.compareTo(MAX_SQRT_RATIO) >= 0)
        ) { "getTickAtSqrtRatio: preconditions failed" }
        val ratio = sqrtPriceX96.shiftLeft(32)
        var r = ratio
        var msb = BigInteger.ZERO
        var f: BigInteger? = null
        f = gt(r, BigInteger("ffffffffffffffffffffffffffffffff", 16)).shiftLeft(7)
        msb = msb.or(f)
        r = r.shiftRight(f.toInt())
        f = gt(r, BigInteger("ffffffffffffffff", 16)).shiftLeft(6)
        msb = msb.or(f)
        r = r.shiftRight(f.toInt())
        f = gt(r, BigInteger("ffffffff", 16)).shiftLeft(5)
        msb = msb.or(f)
        r = r.shiftRight(f.toInt())
        f = gt(r, BigInteger("ffff", 16)).shiftLeft(4)
        msb = msb.or(f)
        r = r.shiftRight(f.toInt())
        f = gt(r, BigInteger("ff", 16)).shiftLeft(3)
        msb = msb.or(f)
        r = r.shiftRight(f.toInt())
        f = gt(r, BigInteger("f", 16)).shiftLeft(2)
        msb = msb.or(f)
        r = r.shiftRight(f.toInt())
        f = gt(r, BigInteger("3", 16)).shiftLeft(1)
        msb = msb.or(f)
        r = r.shiftRight(f.toInt())
        f = gt(r, BigInteger("1", 16))
        msb = msb.or(f)
        r = if (msb >= BigInteger.valueOf(128)) {
            ratio.shiftRight(msb.toInt() - 127)
        } else {
            ratio.shiftLeft(127 - msb.toInt())
        }
        var log2 = msb.subtract(BigInteger.valueOf(128)).shiftLeft(64)
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(63))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(62))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(61))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(60))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(59))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(58))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(57))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(56))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(55))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(54))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(53))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(52))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(51))
        r = r.shiftRight(f.toInt())
        r = r.multiply(r).shiftRight(127)
        f = r.shiftRight(128)
        log2 = log2.or(f.shiftLeft(50))
        val logSqrt10001 = log2.multiply(BigInteger("255738958999603826347141")) // 128.128 number
        val tickLow =
            logSqrt10001.subtract(BigInteger("3402992956809132418596140100660247210")).shiftRight(128).toInt()
        val tickHi = logSqrt10001.add(BigInteger("291339464771989622907027621153398088495")).shiftRight(128).toInt()
        return if (tickLow == tickHi) tickLow else if (getSqrtRatioAtTick(tickHi) <= sqrtPriceX96) tickHi else tickLow
    }
}
