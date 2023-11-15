package io.gladiators.uniswap

import io.gladiators.uniswap.FixedPoint96.RESOLUTION
import org.web3j.abi.datatypes.Address
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import kotlin.math.floor
import kotlin.math.log

fun BigInteger.eth(): BigInteger = this * BigInteger("10").pow(18)
fun Int.eth(): BigInteger = this.toBigInteger().eth()

@JvmInline
value class SqrtPrice(val amount: BigInteger)

fun tickForPrice(price: Double): Int = floor(log(price, 1.0001)).toInt()

fun priceForTick(tick: Int, mc: MathContext = MathContext(20)): BigDecimal = BigDecimal("1.0001").pow(tick, mc)

// Uniswap Dev book
fun priceToSqrtPrice(price: BigInteger): SqrtPrice = SqrtPrice(price.sqrt() * BigInteger.TWO.pow(96))

fun sqrtPriceX96ToPrice(sqrtX96: SqrtPrice): BigDecimal =
    sqrtX96.amount.pow(2).toBigDecimal()
        .divide(2.toBigDecimal().pow(192))

data class BitmapPosition(val wordPos: Int, val bitPos: Int)

/// @notice Computes the position in the mapping where the initialized bit for a tick lives
/// @param tick The tick for which to compute the position
/// @return wordPos The key in the mapping containing the word in which the bit is stored
/// @return bitPos The bit position in the word where the flag is stored
fun bitmapPosition(tick: Int, tickSpacing: Int): BitmapPosition {
    val compressedTick = compress(tick, tickSpacing)
    return bitmapPosition(compressedTick)
}
fun bitmapPosition(compressedTick: Int): BitmapPosition {
    return BitmapPosition(
        BigInteger.valueOf(compressedTick.toLong()).shr(8).toInt(),
        BigInteger.valueOf(compressedTick.toLong()).mod(256.toBigInteger()).toInt()
    )
}

fun compress(tick: Int, tickSpacing: Int): Int = (tick / tickSpacing)
        .let {
            if (tick < 0 && tick % tickSpacing != 0) {
                it - 1// round towards negative infinity
            } else {
                it
            }
        }


/**
 * The trade function #
 *
 * Now that we know what pools are, let’s write the formula of how trading happens in a pool:
 *
 * (x+rΔx)(y−Δy)=k(x+rΔx)(y−Δy)=k
 *
 *     There’s a pool with some amount of token 0 (xx) and some amount of token 1 (yy)
 *     When we buy token 1 for token 0, we give some amount of token 0 to the pool (ΔxΔx).
 *     The pool gives us some amount of token 1 in exchange (ΔyΔy).
 *     The pool also takes a small fee (r=1−swap feer=1−swap fee) from the amount of token 0 we gave.
 *     The reserve of token 0 changes (x+rΔxx+rΔx), and the reserve of token 1 changes as well (y−Δyy−Δy).
 *     The product of updated reserves must still equal kk.
 *
 *     our formula is: Δy = y - k / (x+rΔx)
 */
fun quoteV2Pool(
    reserve0: BigInteger,
    reserve1: BigInteger,
    fee: Int,
    amount0In: BigInteger
): BigInteger {
    require(amount0In >= BigInteger.ZERO)
    val feeFixed = if(fee > 100) fee / 100 else fee
    if (reserve0 == BigInteger.ZERO || reserve1 == BigInteger.ZERO) {
        return BigInteger.ZERO
    }
    val multiplier = 10000.toBigInteger() // instead of division we go with multiplication for math
    val amountInWithFee = amount0In.multiply(multiplier - feeFixed.toBigInteger())
    val numerator = amountInWithFee.multiply(reserve1)
    val denominator = reserve0.multiply(multiplier) + amountInWithFee
    return numerator.divide(denominator)
}

data class Position(val tickLower: Int, val tickUpper: Int, val liquidity: BigInteger)

fun positionForAmounts(sqrtCurrent: SqrtPrice, amount0: BigInteger, amount1: BigInteger, tickLower: Int, tickUpper: Int):
        Position {
    val sqrtAX96 = TickMath.getSqrtRatioAtTick(tickLower)
    val sqrtBX96 = TickMath.getSqrtRatioAtTick(tickUpper)
    return Position(tickLower, tickUpper,
        getLiquidityForAmounts(sqrtCurrent.amount, sqrtAX96, sqrtBX96, amount0, amount1)
    )

}
fun priceMovementPercentage(before: SqrtPrice, after: SqrtPrice, mc: MathContext = MathContext(10)): Float =
    100 - sqrtPriceX96ToPrice(after).divide(sqrtPriceX96ToPrice(before), mc).multiply(100.toBigDecimal()).toFloat()

val Q96 = BigInteger("1000000000000000000000000", 16)


/// @notice Given a tick and a token amount, calculates the amount of token received in exchange
/// @param tick Tick value used to calculate the quote
/// @param baseAmount Amount of token to be converted
/// @param baseToken Address of an ERC20 token contract used as the baseAmount denomination
/// @param quoteToken Address of an ERC20 token contract used as the quoteAmount denomination
/// @return quoteAmount Amount of quoteToken received for baseAmount of baseToken
/// https://github.com/Uniswap/v3-periphery/blob/main/contracts/libraries/OracleLibrary.sol#L43C1-L69C6
fun getQuoteAtTick(tick: Int, baseAmount: BigInteger, baseToken: Address, quoteToken: Address): BigInteger {
    val sqrtX96 = TickMath.getSqrtRatioAtTick(tick)
    return if(sqrtX96 <= IntUtils.MAX_UINT128) {
        val ratioX192 = sqrtX96 * sqrtX96
        val shiftOne = BigInteger.ONE.shl(192)
        if(baseToken.value < quoteToken.value) {
            ratioX192 * baseAmount / shiftOne
        } else {
            shiftOne * baseAmount / ratioX192
        }
    } else {
        val ratioX128 = sqrtX96 * sqrtX96 / (BigInteger.ONE.shl(64))
        val shiftOne = BigInteger.ONE.shl(128)
        if(baseToken.value < quoteToken.value) {
            ratioX128 * baseAmount / shiftOne
        } else {
            shiftOne * baseAmount / ratioX128
        }
    }
}

/// @notice Computes the amount of token0 for a given amount of liquidity and a price range
/// @param sqrtRatioAX96 A sqrt price representing the first tick boundary
/// @param sqrtRatioBX96 A sqrt price representing the second tick boundary
/// @param liquidity The liquidity being valued
/// @return amount0 The amount of token0
//function getAmount0ForLiquidity(
//uint160 sqrtRatioAX96,
//uint160 sqrtRatioBX96,
//uint128 liquidity
//) internal pure returns (uint256 amount0) {
//    if (sqrtRatioAX96 > sqrtRatioBX96) (sqrtRatioAX96, sqrtRatioBX96) = (sqrtRatioBX96, sqrtRatioAX96);
//
//    return
//    FullMath.mulDiv(
//        uint256(liquidity) << FixedPoint96.RESOLUTION,
//        sqrtRatioBX96 - sqrtRatioAX96,
//        sqrtRatioBX96
//    ) / sqrtRatioAX96;
//}
fun getAmount0ForLiquidity(
    sqrtRatioAX96: BigInteger,
    sqrtRatioBX96: BigInteger,
    liquidity: BigInteger
): BigInteger {
    val (sqrtA, sqrtB) = if (sqrtRatioAX96 > sqrtRatioBX96) (sqrtRatioBX96 to sqrtRatioAX96) else (sqrtRatioAX96 to sqrtRatioBX96)
    return liquidity.shl(RESOLUTION).multiply(sqrtB - sqrtA).divide(sqrtB * sqrtA)
}
/// @notice Computes the amount of token1 for a given amount of liquidity and a price range
/// @param sqrtRatioAX96 A sqrt price representing the first tick boundary
/// @param sqrtRatioBX96 A sqrt price representing the second tick boundary
/// @param liquidity The liquidity being valued
/// @return amount1 The amount of token1
//function getAmount1ForLiquidity(
//uint160 sqrtRatioAX96,
//uint160 sqrtRatioBX96,
//uint128 liquidity
//) internal pure returns (uint256 amount1) {
//    if (sqrtRatioAX96 > sqrtRatioBX96) (sqrtRatioAX96, sqrtRatioBX96) = (sqrtRatioBX96, sqrtRatioAX96);
//
//    return FullMath.mulDiv(liquidity, sqrtRatioBX96 - sqrtRatioAX96, FixedPoint96.Q96);
//}
fun getAmount1ForLiquidity(
    sqrtRatioAX96: BigInteger,
    sqrtRatioBX96: BigInteger,
    liquidity: BigInteger
): BigInteger {
    val (sqrtA, sqrtB) = if (sqrtRatioAX96 > sqrtRatioBX96) (sqrtRatioBX96 to sqrtRatioAX96) else (sqrtRatioAX96 to sqrtRatioBX96)
    return liquidity.multiply(sqrtB - sqrtA).divide(Q96)
}


// !!!!! Following code copypasted from https://github.com/Convexus-Protocol/AMM-SCORE/blob/8f4fc9faf574078885551a471d6d504a1cbc12d1/Convexus-Periphery/Librairies/src/main/java/exchange/convexus/periphery/librairies/LiquidityAmounts.java#L72

/**
 * @notice Computes the amount of token1 for a given amount of liquidity and a price range
 * @param sqrtRatioAX96 A sqrt price representing the first tick boundary
 * @param sqrtRatioBX96 A sqrt price representing the second tick boundary
 * @param liquidity The liquidity being valued
 * @return amount1 The amount of token1
 */
private fun getLiquidityForAmount1(
    sqrtRatioAX96: BigInteger,
    sqrtRatioBX96: BigInteger,
    amount1: BigInteger
): BigInteger {
    var sqrtRatioAX96 = sqrtRatioAX96
    var sqrtRatioBX96 = sqrtRatioBX96
    if (sqrtRatioAX96.compareTo(sqrtRatioBX96) > 0) {
        val tmp = sqrtRatioAX96
        sqrtRatioAX96 = sqrtRatioBX96
        sqrtRatioBX96 = tmp
    }
    return amount1.multiply(Q96).divide(sqrtRatioBX96.subtract(sqrtRatioAX96))
}

/**
 * @notice Computes the amount of token0 for a given amount of liquidity and a price range
 * @param sqrtRatioAX96 A sqrt price representing the first tick boundary
 * @param sqrtRatioBX96 A sqrt price representing the second tick boundary
 * @param liquidity The liquidity being valued
 * @return amount0 The amount of token0
 */
private fun getLiquidityForAmount0(
    sqrtRatioAX96: BigInteger,
    sqrtRatioBX96: BigInteger,
    amount0: BigInteger
): BigInteger {
    var sqrtRatioAX96 = sqrtRatioAX96
    var sqrtRatioBX96 = sqrtRatioBX96
    if (sqrtRatioAX96 > sqrtRatioBX96) {
        val tmp = sqrtRatioAX96
        sqrtRatioAX96 = sqrtRatioBX96
        sqrtRatioBX96 = tmp
    }
    val intermediate: BigInteger = sqrtRatioAX96.multiply(sqrtRatioBX96).divide(Q96)
    return amount0.multiply(intermediate).divide(sqrtRatioBX96.subtract(sqrtRatioAX96))
}

/**
 * @notice Computes the maximum amount of liquidity received for a given amount of token0, token1, the current
 * pool prices and the prices at the tick boundaries
 * @param sqrtRatioX96 A sqrt price representing the current pool prices
 * @param sqrtRatioAX96 A sqrt price representing the first tick boundary
 * @param sqrtRatioBX96 A sqrt price representing the second tick boundary
 * @param amount0 The amount of token0 being sent in
 * @param amount1 The amount of token1 being sent in
 * @return liquidity The maximum amount of liquidity received
 */
fun getLiquidityForAmounts(
    sqrtRatioX96: BigInteger,
    sqrtRatioAX96: BigInteger,
    sqrtRatioBX96: BigInteger,
    amount0: BigInteger,
    amount1: BigInteger
): BigInteger {
    val (sqrtRatioA, sqrtRatioB) =
        if(sqrtRatioAX96 > sqrtRatioBX96) sqrtRatioBX96 to sqrtRatioAX96 else sqrtRatioAX96 to sqrtRatioBX96
    return if (sqrtRatioX96 <= sqrtRatioA) {
        getLiquidityForAmount0(sqrtRatioA, sqrtRatioB, amount0)
    } else if (sqrtRatioX96 < sqrtRatioB) {
        val liquidity0: BigInteger = getLiquidityForAmount0(sqrtRatioX96, sqrtRatioB, amount0)
        val liquidity1: BigInteger = getLiquidityForAmount1(sqrtRatioA, sqrtRatioX96, amount1)
        if (liquidity0 < liquidity1) liquidity0 else liquidity1
    } else {
        getLiquidityForAmount1(sqrtRatioA, sqrtRatioB, amount1)
    }
}

fun feeForV2Pool(
    reserve0: BigInteger,
    reserve1: BigInteger,
    amount0In: BigInteger,
    amount1Out: BigInteger
): Int {
    require(amount0In > BigInteger.ZERO)
    require(amount1Out > BigInteger.ZERO)
    require(reserve0 > BigInteger.ZERO)
    require(reserve1 > BigInteger.ZERO)

    val numerator = reserve0 * amount1Out * 1000000.toBigInteger()
    val denominator = amount0In * (reserve1 - amount1Out)
    return (1000000.toBigInteger() - numerator.divide(denominator)).roundToNearest500().intValueExact()
}

private fun BigInteger.roundToNearest500(): BigInteger {
    val remainder = this.remainder(BigInteger.valueOf(500))
    val half500 = BigInteger.valueOf(250)

    return if (remainder >= half500) {
        this + BigInteger.valueOf(500) - remainder
    } else {
        this - remainder
    }
}


