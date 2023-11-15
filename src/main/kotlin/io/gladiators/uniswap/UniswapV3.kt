package io.gladiators.uniswap

import io.gladiators.MulticallDefer
import io.gladiators.multicall
import io.gladiators.solidity.TickLens
import io.gladiators.solidity.UniswapV2Pool
import io.gladiators.solidity.UniswapV3Pool
import io.gladiators.web3.Web3Context
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.DynamicArray
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.tx.ReadonlyTransactionManager
import org.web3j.tx.gas.StaticGasProvider
import java.math.BigDecimal
import java.math.BigInteger

fun Web3Context.tickLens(): TickLens = TickLens.load(
    addressBook.uniswap().tickLens,
    web3j, web3Defaults.credentials, web3Defaults.gasProvider
)

fun TickLens.loadPopulatedTicksTx(poolV3: String, indexRange: IntRange): List<RemoteFunctionCall<MutableList<Any?>>> =
    indexRange
        .map {
            this.getPopulatedTicksInWord(poolV3, it.toBigInteger())
        }
        .toList()

data class TickPosition(
    val tick: Int, val tickHigh: Int, val zeroForOnePrice: BigDecimal, val amount0: BigInteger, val amount1:
    BigInteger, val liquidity: BigInteger
)

/**
 * Resolve quote directly from Pool with all math calculations in JVM
 */
fun quoteV2Pool(address: String, fee: Int, zeroForOne: Boolean, inAmountWei: BigInteger):
        MulticallDefer {
    val pair = UniswapV2Pool.load(address, null,
        ReadonlyTransactionManager(null, null), StaticGasProvider(BigInteger.ZERO, BigInteger.ZERO))
    return MulticallDefer(pair.contractAddress, pair.reserves) { (reserve0, reserve1, _) ->
        listOf(
            Uint256(
                quoteV2Pool(
                    if (zeroForOne) reserve0.value as BigInteger else reserve1.value as BigInteger,
                    if (zeroForOne) reserve1.value as BigInteger else reserve0.value as BigInteger,
                    fee,
                    inAmountWei
                )
            )
        )
    }
}
fun Web3Context.poolPositions(poolV3: Address): List<TickPosition> {
    val pool = UniswapV3Pool.load(poolV3.value, web3j, web3Defaults.credentials, web3Defaults.gasProvider)
    val currentTick = pool.slot0().send().component2().toInt()
    val tickSpacing = pool.tickSpacing().send().toInt()
    val currentTickBottom = (currentTick / tickSpacing) * tickSpacing
    val lens = tickLens()
    val ticks = lens.loadPopulatedTicksTx(
        pool.contractAddress,
        bitmapPosition(TickMath.MIN_TICK, tickSpacing).wordPos..
                bitmapPosition(TickMath.MAX_TICK, tickSpacing).wordPos
    )
        .chunked(80)
        .flatMap { batch ->
            val types = multicall {
                address(Address(lens.contractAddress))
                batch.forEach { call(it) }
            }
            types.flatMap { (it[0] as DynamicArray<TickLens.PopulatedTick>).value }
        }

    require(ticks.sumOf { it.liquidityNet } == BigInteger.ZERO)
    val minTick = ticks.minOf { it.tick }.toInt()
    val maxTick = ticks.maxOf { it.tick }.toInt()
    val spacedTickRange = minTick until maxTick step tickSpacing
    val netsPerTick = ticks.associate { it.tick.toInt() to it.liquidityNet }
    val initialLiquidity = BigInteger.ZERO
    return spacedTickRange.fold((initialLiquidity to listOf<TickPosition>())) { (liquidityAcc, positionAcc), activeTick ->
        val liquidityDelta = netsPerTick.getOrDefault(activeTick, BigInteger.ZERO)
        val updatedLiquidity = liquidityAcc + liquidityDelta
        val lowTick = activeTick
        val highTick = activeTick + tickSpacing
        val sqrtA = TickMath.getSqrtRatioAtTick(lowTick)
        val sqrtB = TickMath.getSqrtRatioAtTick(highTick)
        val price = priceForTick(activeTick)
        val position = if (activeTick < currentTickBottom) {
            val amount1 = getAmount1ForLiquidity(sqrtA, sqrtB, updatedLiquidity)
            TickPosition(activeTick, highTick, price, BigInteger.ZERO, amount1, updatedLiquidity)
        } else if (activeTick == currentTickBottom) {
            val sqrtCurrent = TickMath.getSqrtRatioAtTick(currentTick)
            val amount0 = getAmount0ForLiquidity(sqrtCurrent, sqrtB, updatedLiquidity)
            val amount1 = getAmount1ForLiquidity(sqrtA, sqrtCurrent, updatedLiquidity)
            TickPosition(activeTick, highTick, price, amount0, amount1, updatedLiquidity)
        } else {
            val amount0 = getAmount0ForLiquidity(sqrtA, sqrtB, updatedLiquidity)
            TickPosition(activeTick, highTick, price, amount0, BigInteger.ZERO, updatedLiquidity)
        }
        updatedLiquidity to (positionAcc + position)
    }.second
}
