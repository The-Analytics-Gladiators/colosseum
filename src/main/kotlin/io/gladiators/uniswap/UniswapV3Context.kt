package io.gladiators.uniswap

import com.esaulpaugh.headlong.util.FastHex
import io.github.oshai.kotlinlogging.KotlinLogging
import io.gladiators.chain.erc20
import io.gladiators.multicall
import io.gladiators.solidity.NonFungiblePositionManager
import io.gladiators.solidity.Quoterv2
import io.gladiators.solidity.UniswapSwapRouterV2
import io.gladiators.solidity.UniswapV3Pool
import io.gladiators.web3.Web3Context
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.generated.*
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.protocol.core.methods.response.TransactionReceipt
import java.math.BigInteger


private val logger = KotlinLogging.logger { }

data class UniswapPoolV3Context<T: Web3Context>(
    val web3Context: T,
    val pool: String,
    val tickSpacing: Int,
    val currentTick: Int,
    val liquidity: BigInteger,
    val sqrtPriceCurrent: SqrtPrice,
    val fee: Int,
    val token0: String,
    val token1: String,
    val feeGrowthGlobal0X128: BigInteger,
    val feeProtocol: Int,
    val feeGrowthGlobal1X128: BigInteger
)

fun Web3Context.uniswapPositionManager(): NonFungiblePositionManager =
    NonFungiblePositionManager.load(
        addressBook.uniswap().positionManager, web3j,
        web3Defaults.credentials, web3Defaults.gasProvider
    )

fun Web3Context.uniswapRouter(): UniswapSwapRouterV2 =
    UniswapSwapRouterV2.load(
        addressBook.uniswap().routerV2, web3j,
        web3Defaults.credentials, web3Defaults.gasProvider
    )

fun Web3Context.uniswapQuoter(): Quoterv2 =
    Quoterv2.load(
        addressBook.uniswap().quoterV2, web3j,
        web3Defaults.credentials, web3Defaults.gasProvider
    )

fun <T> Web3Context.withinPoolV3(pool: String, function: UniswapPoolV3Context<Web3Context>.() -> T): T {
    val poolV3 = UniswapV3Pool.load(
        pool,
        web3j,
        web3Defaults.credentials,
        web3Defaults.gasProvider
    )
    require(poolV3.factory().send().equals(addressBook.uniswap().factory, ignoreCase = true)) { "This is not Uniswap pool $pool" }
    val call = multicall {
        contract(poolV3)
        call(poolV3.tickSpacing())
        call(poolV3.slot0())
        call(poolV3.liquidity())
        call(poolV3.token0())
        call(poolV3.token1())
        call(poolV3.fee())
        call(poolV3.feeGrowthGlobal0X128())
        call(poolV3.feeGrowthGlobal1X128())
    }
    val tickSpacing = (call[0][0] as Int24).value.toInt()
    val sqrtPrice = SqrtPrice((call[1][0] as Uint160).value)
    val currentTick = (call[1][1] as Int24).value.toInt()
    val feeProtocol = (call[1][5] as Uint8).value.toInt()
    val liquidity = (call[2][0] as Uint128).value
    val token0 = (call[3][0] as Address).value
    val token1 = (call[4][0] as Address).value
    val fee = (call[5][0] as Uint24).value.toInt()
    val feeGrowthGlobal0 = (call[6][0] as Uint256).value
    val feeGrowthGlobal1 = (call[7][0] as Uint256).value
    return UniswapPoolV3Context(
        this,
        pool,
        tickSpacing,
        currentTick,
        liquidity,
        sqrtPrice,
        fee,
        token0,
        token1,
        feeGrowthGlobal0,
        feeProtocol,
        feeGrowthGlobal1
    ).let(function)
}

fun UniswapPoolV3Context<out Web3Context>.poolV3(): UniswapV3Pool =
    UniswapV3Pool.load(pool, web3Context.web3j, web3Context.web3Defaults.credentials, web3Context.web3Defaults.gasProvider)

fun UniswapPoolV3Context<out Web3Context>.approveRouterTx(token: String, amount: BigInteger):
        RemoteFunctionCall<TransactionReceipt> = web3Context.let {
    it.erc20(token).approve(it.uniswapRouter().contractAddress, amount)
}

fun UniswapPoolV3Context<out Web3Context>.approveRouter(token: String, amount: BigInteger): TransactionReceipt =
    approveRouterTx(token, amount).send()

fun UniswapPoolV3Context<out Web3Context>.approveRouter(token: Address, amount: BigInteger): TransactionReceipt =
    approveRouterTx(token.value, amount).send()

data class SwapEstimate(val amountIn: BigInteger, val amountOut: BigInteger)

// returns null if swap is not possible .... complex
fun UniswapPoolV3Context<out Web3Context>.swapEstimateOneTick(tokenIn: String, amount: BigInteger): SwapEstimate? {
    val zeroForOne = tokenIn != token1
    val nextTick = nextInitializerTickWithinOneWord(zeroForOne) ?: return null
    val sqrtRatioAtNext = TickMath.getSqrtRatioAtTick(nextTick)
    return swapEstimateV3OneTick(amount, sqrtPriceCurrent, SqrtPrice(sqrtRatioAtNext), liquidity, fee)
}

fun swapEstimateV3OneTick(amount: BigInteger, sqrtPriceCurrent: SqrtPrice, sqrtPriceNext: SqrtPrice,
                          liquidity: BigInteger,
                          fee: Int): SwapEstimate {
    val swapped = SwapMath.computeSwapStep(sqrtPriceCurrent.amount, sqrtPriceNext.amount, liquidity, amount, fee)
    return SwapEstimate(swapped.amountIn, swapped.amountOut)
}

/// @notice Returns the next initialized tick contained in the same word (or adjacent word) as the tick that is either
/// to the left (less than or equal to) or right (greater than) of the given tick
/// @param tick The starting tick
/// @param tickSpacing The spacing between usable ticks
/// @param lte Whether to search for the next initialized tick to the left (less than or equal to the starting tick)
/// @return next The next initialized or uninitialized tick up to 256 ticks away from the current tick
/// @return initialized Whether the next tick is initialized, as the function only searches within up to 256 ticks
fun UniswapPoolV3Context<out Web3Context>.nextInitializerTickWithinOneWord(toTheRight: Boolean,
                                                                           tick: Int = currentTick
): Int? {
    val compressedTick = compress(tick, tickSpacing)
    return if(toTheRight) {
        val bitmapPosition = bitmapPosition(compressedTick)
        val mask = BigInteger.ONE.shl(bitmapPosition.bitPos) - BigInteger.ONE + (BigInteger.ONE.shl(bitmapPosition.bitPos))
        val masked = poolV3().tickBitmap(bitmapPosition.wordPos.toBigInteger()).send().and(mask)
        val initialized = masked != BigInteger.ZERO
        if(initialized) {
            val mostSignificantBit =  masked.bitLength() - 1
            (compressedTick - (bitmapPosition.bitPos - mostSignificantBit)) * tickSpacing
        } else {
            null
//            (compressedTick - bitmapPosition.bitPos) * tickSpacing
        }
    } else {
        val bitmapPosition = bitmapPosition(compressedTick + 1)
        val mask = (BigInteger.ONE.shl(bitmapPosition.bitPos) - BigInteger.ONE).not()
        val masked = poolV3().tickBitmap(bitmapPosition.wordPos.toBigInteger()).send().and(mask)
        val initialized = masked != BigInteger.ZERO
        if(initialized) {
            val leastSignificantBit = masked.lowestSetBit
            (compressedTick + 1 + (leastSignificantBit - bitmapPosition.bitPos)) * tickSpacing
        } else {
            null
//            (compressedTick + 1 + (IntUtils.MAX_UINT8.toInt()- bitmapPosition.bitPos)) * tickSpacing
        }
    }
}
fun UniswapPoolV3Context<out Web3Context>.swap(tokenIn: String, amountIn: BigInteger, slippage: Double = 0.005): TransactionReceipt? =
    if (token0 == tokenIn) {
        swapTx(amountIn, BigInteger.ZERO, slippage)?.send()
    } else {
        swapTx(BigInteger.ZERO, amountIn, slippage)?.send()
    }
fun UniswapPoolV3Context<out Web3Context>.swapExactOutput(tokenOut: String, amountOut: BigInteger, slippage: Double = 0.005): TransactionReceipt? =
    if (token0 == tokenOut) {
        swapExactOutputTx(amountOut, BigInteger.ZERO, slippage)?.send()
    } else {
        swapExactOutputTx(BigInteger.ZERO, amountOut, slippage)?.send()
    }
fun UniswapPoolV3Context<out Web3Context>.swap(
    amount0: BigInteger,
    amount1: BigInteger,
    slippage: Double = 0.005,
    ticksAllowed: Int = Int.MAX_VALUE
): TransactionReceipt? =
    swapTx(amount0, amount1, slippage, ticksAllowed)?.send()

fun UniswapPoolV3Context<out Web3Context>.swapTx(
    amount0: BigInteger,
    amount1: BigInteger,
    slippage: Double = 0.005,
    ticksAllowed: Int = Int.MAX_VALUE
): RemoteFunctionCall<TransactionReceipt>? {
    require(
        (amount0 > BigInteger.ZERO || amount1 > BigInteger.ZERO) &&
                !(amount0 > BigInteger.ZERO && amount1 > BigInteger.ZERO)
    ) { "Zero for one or one for zero only" }
    val (tokenIn, tokenOut) = if (amount0 > BigInteger.ZERO) token0 to token1 else token1 to token0
    val amountIn = if (amount0 > BigInteger.ZERO) amount0 else amount1
    val maxSqrtRatio = if (amount0 > BigInteger.ZERO)
        TickMath.getSqrtRatioAtTick(
            (currentTick.toLong() - ticksAllowed).coerceAtLeast(TickMath.MIN_TICK.toLong() + 1).toInt()
        )
    else
        TickMath.getSqrtRatioAtTick(
            (currentTick.toLong() + ticksAllowed).coerceAtMost(TickMath.MAX_TICK.toLong() - 1).toInt()
        )

    val router = web3Context.uniswapRouter()
    val minAmountOut = quoteSingleInput(tokenIn, tokenOut, amountIn, maxSqrtRatio)
        .let {
            it - (it.toBigDecimal() * slippage.toBigDecimal()).toBigInteger()
        }
    return router.exactInputSingle(
            UniswapSwapRouterV2.ExactInputSingleParams(
                tokenIn,
                tokenOut,
                fee.toBigInteger(),
                web3Context.web3Defaults.credentials.address,
                amountIn,
                minAmountOut,
                maxSqrtRatio
            ),
            BigInteger.ZERO
        )
}

fun UniswapPoolV3Context<out Web3Context>.quoteSingleInput(
    tokenIn: String,
    tokenOut: String,
    amountIn: BigInteger,
    maxSqrtRatio: BigInteger = BigInteger.ZERO
): BigInteger {
    return web3Context.uniswapQuoter().quoteExactInputSingle(
        Quoterv2.QuoteExactInputSingleParams(
            tokenIn, tokenOut,
            amountIn,
            fee.toBigInteger(),
            maxSqrtRatio
        )
    ).send().component1()
}
fun UniswapPoolV3Context<out Web3Context>.swapExactOutputTx(
    amount0: BigInteger,
    amount1: BigInteger,
    slippage: Double = 0.005,
    ticksAllowed: Int = Int.MAX_VALUE
): RemoteFunctionCall<TransactionReceipt>? {
    require(
        (amount0 > BigInteger.ZERO || amount1 > BigInteger.ZERO) && !(amount0 > BigInteger.ZERO && amount1 > BigInteger.ZERO)
    ) { "Zero for one or one for zero only" }
    val (tokenIn, tokenOut) = if (amount0 > BigInteger.ZERO) token1 to token0 else token0 to token1
    val amountOut = if (amount0 > BigInteger.ZERO) amount0 else amount1
    val maxSqrtRatio = if (amount0 > BigInteger.ZERO)
        TickMath.getSqrtRatioAtTick(
            (currentTick.toLong() + ticksAllowed).coerceAtMost(TickMath.MAX_TICK.toLong() - 1).toInt()
        )
    else
        TickMath.getSqrtRatioAtTick(
            (currentTick.toLong() - ticksAllowed).coerceAtLeast(TickMath.MIN_TICK.toLong() + 1).toInt()
        )

    val router = web3Context.uniswapRouter()
    val minAmountOut = quoteSingleOutput(tokenIn, tokenOut, amountOut, maxSqrtRatio)
        .let {
            it - (it.toBigDecimal() * slippage.toBigDecimal()).toBigInteger()
        }
    return router.exactOutputSingle(
        UniswapSwapRouterV2.ExactOutputSingleParams(
            tokenIn,
            tokenOut,
            fee.toBigInteger(),
            web3Context.web3Defaults.credentials.address,
            amountOut,
            minAmountOut,
            maxSqrtRatio
        ),
        BigInteger.ZERO
    )
}

fun UniswapPoolV3Context<out Web3Context>.quoteSingleOutput(
    tokenIn: String,
    tokenOut: String,
    amountOut: BigInteger,
    maxSqrtRatio: BigInteger
): BigInteger {
    return web3Context.uniswapQuoter().quoteExactOutputSingle(
        Quoterv2.QuoteExactOutputSingleParams(
            tokenIn, tokenOut,
            amountOut,
            fee.toBigInteger(),
            maxSqrtRatio
        )
    ).send().component1()
}
@JvmInline
value class TokenId(val id: BigInteger)

fun UniswapPoolV3Context<out Web3Context>.mint(amount0Max: BigInteger, amount1Max: BigInteger, tickRange: IntRange): TokenId? {
    val tkn0 = web3Context.erc20(token0)
    val tkn1 = web3Context.erc20(token1)
    val allow0 = tkn0.allowance(web3Context.web3Defaults.credentials.address, web3Context.addressBook.uniswap().positionManager).send()
    val allow1 = tkn1.allowance(web3Context.web3Defaults.credentials.address, web3Context.addressBook.uniswap().positionManager).send()
    if (allow0 < amount0Max || allow1 < amount1Max) {
        val positionManager = web3Context.uniswapPositionManager()
        tkn0.approve(positionManager.contractAddress, amount0Max).send()
        tkn1.approve(positionManager.contractAddress, amount1Max).send()
    }
    val positionManager = web3Context.uniswapPositionManager()
    val currentTokensCount = positionManager.balanceOf(web3Context.web3Defaults.credentials.address).send()
    val mintResult = mintTx(amount0Max, amount1Max, tickRange).send()
    return if (!mintResult.isStatusOK) {
        logger.error { "mint failure ${mintResult.revertReason}" }
        null
    } else {
        TokenId(positionManager.tokenOfOwnerByIndex(web3Context.web3Defaults.credentials.address, currentTokensCount).send())
    }
}


fun UniswapPoolV3Context<out Web3Context>.loadMintedTokens(): List<TokenId> {
    val positionManager = web3Context.uniswapPositionManager()
    val currentTokensCount = positionManager.balanceOf(web3Context.web3Defaults.credentials.address).send().toInt()
    val result = web3Context.multicall {
        contract(positionManager)
        (0 until currentTokensCount).map {
            call(positionManager.tokenOfOwnerByIndex(web3Context.web3Defaults.credentials.address, it.toBigInteger()))
        }
    }
    return result.map {
        TokenId((it[0] as Uint256).value)
    }
}

typealias Token0Token1Amounts = Pair<BigInteger, BigInteger>

fun UniswapPoolV3Context<out Web3Context>.estimateMint(
    amount0Max: BigInteger,
    amount1Max: BigInteger,
    tickRange: IntRange,
): Token0Token1Amounts {
    require(tickRange.first <= tickRange.last) { "tickRange must be greater than 0" }
    val bottomTick = Math.floorDiv(tickRange.first, tickSpacing) * tickSpacing
    val upperTick = ((tickRange.last / tickSpacing) * tickSpacing).let {
        if (it == bottomTick) bottomTick + tickSpacing else it
    }
    val sqrtAX96 = TickMath.getSqrtRatioAtTick(bottomTick)
    val sqrtBX96 = TickMath.getSqrtRatioAtTick(upperTick)
    val position = positionForAmounts(sqrtPriceCurrent, amount0Max, amount1Max, bottomTick, upperTick)
    return if (currentTick < bottomTick) {
        val amount0 = SqrtPriceMath.getAmount0Delta(sqrtAX96, sqrtBX96, position.liquidity)
        amount0 to BigInteger.ZERO
    } else if (currentTick < upperTick) {
        val amount0 = SqrtPriceMath.getAmount0Delta(sqrtPriceCurrent.amount, sqrtBX96, position.liquidity)
        val amount1 = SqrtPriceMath.getAmount1Delta(sqrtAX96, sqrtPriceCurrent.amount, position.liquidity)
        amount0 to amount1
    } else {
        val amount1 = SqrtPriceMath.getAmount1Delta(sqrtAX96, sqrtBX96, position.liquidity)
        BigInteger.ZERO to amount1
    }
}

fun UniswapPoolV3Context<out Web3Context>.mintTx(
    amount0Max: BigInteger,
    amount1Max: BigInteger,
    tickRange: IntRange,
    slippage: Double = 0.001
): RemoteFunctionCall<TransactionReceipt> {
    val bottomTick = Math.floorDiv(tickRange.first, tickSpacing) * tickSpacing
    val upperTick = ((tickRange.last / tickSpacing) * tickSpacing).let {
        if (it == bottomTick) bottomTick + tickSpacing else it
    }
    val (minAmount0, minAmount1) = estimateMint(amount0Max, amount1Max, tickRange)
    val (minAmount0Expected, minAmount1Expected) = listOf(minAmount0, minAmount1)
        .map {
            it - (it.toBigDecimal() * slippage.toBigDecimal()).toBigInteger()
        }

    val mintParams = NonFungiblePositionManager.MintParams(
        token0,
        token1,
        fee.toBigInteger(),
        bottomTick.toBigInteger(),
        upperTick.toBigInteger(),
        amount0Max,
        amount1Max,
        minAmount0Expected,
        minAmount1Expected,
        web3Context.web3Defaults.credentials.address,
        ((System.currentTimeMillis() / 1000) + 10).toBigInteger()
    )
    return web3Context.uniswapPositionManager().mint(mintParams, BigInteger.ZERO)
}

fun UniswapPoolV3Context<out Web3Context>.closePosition(tokenId: TokenId, recipient: String = web3Context.web3Defaults.credentials.address):
        TransactionReceipt {
    fun String.encode(): ByteArray = FastHex.decode(this.removePrefix("0x"))
    return web3Context.uniswapPositionManager().multicall(
        listOf(
            decreaseLiquidityInPoolTx(tokenId, positions(tokenId).liquidity).encodeFunctionCall().encode(),
            collectTx(tokenId, recipient).encodeFunctionCall().encode(),
            burnTx(tokenId).encodeFunctionCall().encode()
        ),
        BigInteger.ZERO).send()
}
fun UniswapPoolV3Context<out Web3Context>.collectTx(
    tokenId: TokenId,
    recipient: String = web3Context.web3Defaults.credentials.address,
    amount0Max: BigInteger = IntUtils.MAX_UINT128,
    amount1Max: BigInteger = IntUtils.MAX_UINT128
): RemoteFunctionCall<TransactionReceipt> {
    return web3Context.uniswapPositionManager().collect(NonFungiblePositionManager
        .CollectParams(tokenId.id, recipient, amount0Max, amount1Max), BigInteger.ZERO)
}

fun UniswapPoolV3Context<out Web3Context>.decreaseLiquidityInPoolTx(
    tokenId: TokenId,
    liquidityBurnPortion: BigInteger,
    amount0Min: BigInteger = BigInteger.ZERO,
    amount1Min: BigInteger = BigInteger.ZERO,
    deadline: BigInteger = ((System.currentTimeMillis() / 1000) + 60).toBigInteger()
): RemoteFunctionCall<TransactionReceipt> {
    require(liquidityBurnPortion > BigInteger.ZERO)
    return web3Context.uniswapPositionManager().decreaseLiquidity(
        NonFungiblePositionManager.DecreaseLiquidityParams(
            tokenId.id,
            liquidityBurnPortion,
            amount0Min,
            amount1Min,
            deadline
        ), BigInteger.ZERO
    )
}

data class PositionInfo(
    val nonce: BigInteger,
    val operator: Address,
    val token0: String,
    val token1: String,
    val fee: Int,
    val tickLower: Int,
    val tickUpper: Int,
    val liquidity: BigInteger,
    val feeGrowthInside0LastX128: BigInteger,
    val feeGrowthInside1LastX128: BigInteger,
    val token0Owned: BigInteger,
    val token1Owned: BigInteger
)

fun UniswapPoolV3Context<out Web3Context>.positions(tokenId: TokenId): PositionInfo {
    val positionManager = web3Context.uniswapPositionManager()
    val result = positionManager.positions(tokenId.id).send()
    return PositionInfo(
        result.component1(),
        Address(result.component2()),
        result.component3(),
        result.component4(),
        result.component5().toInt(),
        result.component6().toInt(),
        result.component7().toInt(),
        result.component8(),
        result.component9(),
        result.component10(),
        result.component11(),
        result.component12(),
    )
}

fun UniswapPoolV3Context<out Web3Context>.burn(tokenId: TokenId): TransactionReceipt {
    val positionManager = web3Context.uniswapPositionManager()
    val approveResult = positionManager.approve(positionManager.contractAddress, tokenId.id).send()
    return if (!approveResult.isStatusOK) {
        approveResult
    } else {
        burnTx(tokenId).send()
    }
}

fun UniswapPoolV3Context<out Web3Context>.burnTx(tokenId: TokenId): RemoteFunctionCall<TransactionReceipt> {
    val positionManager = web3Context.uniswapPositionManager()
    return positionManager.burn(tokenId.id, BigInteger.ZERO)
}
