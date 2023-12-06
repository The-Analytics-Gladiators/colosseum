package io.gladiators.cex

import com.github.benmanes.caffeine.cache.Caffeine
import io.gladiators.chain.BnbTokens
import io.gladiators.chain.erc20
import io.gladiators.solidity.Erc20
import io.gladiators.web3.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import org.web3j.abi.datatypes.Address
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode
import java.time.Duration
import kotlin.time.Duration.Companion.seconds

private data class TokenCacheId(val blockchain: String, val address: String)


private val priceCache = Caffeine.newBuilder()
    .expireAfterWrite(Duration.ofHours(1))
    .maximumSize(1000)
    .build<TokenCacheId, Double> { key ->
        val client = HttpClient(CIO) {
            followRedirects = true
            expectSuccess = true
            install(HttpTimeout) {
                connectTimeoutMillis = 1.seconds.inWholeMilliseconds
                requestTimeoutMillis = 2.seconds.inWholeMilliseconds
                socketTimeoutMillis = 1.seconds.inWholeMilliseconds
            }
            install(ContentNegotiation) {
                json()
            }
        }
        val chain = key.blockchain
        val address = key.address
        runBlocking {
            val response = client.get("""https://api.coingecko.com/api/v3/simple/token_price/$chain?contract_addresses=${address}&vs_currencies=usd""".trimMargin()) {
                header("accept", "application/json")
            }
            require(response.status.isSuccess()) { "coingecko request failed ${response.body<String>()}" }
            val resp: Map<String, Map<String, Double>> = response.body()
            resp[address]!!["usd"]!!.apply { require(this > 0) }
        }.apply {
            client.close()
        }
    }

private fun chainToGeckoName(context: Web3Context): String = when(context.blockchainConstants.chain) {
    Chain.POLYGON -> "polygon-pos"
    Chain.BSC -> "binance-smart-chain"
    Chain.OPTIMISM -> "optimistic-ethereum"
    Chain.ARBITRUM_ONE -> "arbitrum-one"
    Chain.ETH -> "ethereum"
    Chain.HARDHAT -> when(context) {
        is BinanceContext -> "binance-smart-chain"
        is PolygonContext -> "polygon-pos"
        is OptimismContext -> "optimistic-ethereum"
        is EthereumContext -> "ethereum"
        else -> throw IllegalArgumentException("No cex quote for $context")
    }
}

private val decimalsCache = Caffeine.newBuilder()
    .expireAfterWrite(Duration.ofHours(24))
    .maximumSize(1000)
    .build<String, Int>()

fun Web3Context.usdForOneToken(token: String): Double =
    priceCache.get(TokenCacheId(chainToGeckoName(this), token ))
fun usdForOneToken(token: Erc20, chain: String): Double = priceCache.get(TokenCacheId(chain, token.contractAddress))

fun Web3Context.bnbToUSD(amountWei: BigInteger) =
    bnbToUsd( amountWei)

fun Web3Context.tokenToUSD(from: Erc20, amountWei: BigInteger): BigDecimal =
    tokenToUSD(from.contractAddress, amountWei)

fun Web3Context.tokenToToken(from: Erc20, amountWei: BigInteger, to: Erc20): BigInteger {
    val token0Usd = tokenToUSD(from, amountWei)
    return usdToToken(to, token0Usd, chainToGeckoName(this))
}

fun Web3Context.usdToToken(token: String, amountWei: BigDecimal): BigInteger =
    usdToToken(amountWei, usdForOneToken(token), decimalsForToken(token))

fun usdToToken( to: Erc20, amountWei: BigDecimal, chain: String): BigInteger {
    return usdToToken(amountWei, usdForOneToken(to, chain), decimalsForToken(to))
}

fun usdToToken(amountWei: BigDecimal, tokenPrice: Double, decimals: Int):BigInteger {
    val weiInOneToken = BigInteger.valueOf(10).pow(decimals).toBigDecimal()
    return weiInOneToken.multiply(amountWei).divide(tokenPrice.toBigDecimal(), MathContext(15))
        .setScale(0, RoundingMode.FLOOR)
        .toBigInteger()
}

fun Web3Context.tokenToUSD(
    token: String, amountWei: BigInteger, mathContext: MathContext = MathContext(15)
): BigDecimal {
    return tokenToUSD(amountWei, usdForOneToken(token), decimalsForToken(token), mathContext)
}

fun tokenToUSD(amountWei: BigInteger, tokenPrice: Double, decimals: Int, mathContext: MathContext = MathContext(15)): BigDecimal {
    val weiCount = BigInteger.valueOf(10).pow(decimals)
    return amountWei.toBigDecimal().multiply(tokenPrice.toBigDecimal()).divide(weiCount.toBigDecimal(), mathContext)
}

fun tokenToUSD(
    from: Erc20, amountWei: BigInteger,
    chain: String,
    mathContext: MathContext = MathContext(10)
): BigDecimal {
    val tokenPrice = usdForOneToken(from, chain)
    val weiCount = BigInteger.valueOf(10).pow(decimalsForToken(from))
    return amountWei.toBigDecimal().multiply(tokenPrice.toBigDecimal()).divide(weiCount.toBigDecimal(), mathContext)
}

fun Web3Context.bnbToUsd(amountWei: BigInteger): BigDecimal =
    tokenToUSD(erc20(BnbTokens.WBNB.address), amountWei, chainToGeckoName(this))

fun Web3Context.decimalsForToken(token: String): Int = decimalsCache.get(token) { decimalsForTokenWithoutCache(erc20(token)) }

fun decimalsForToken(token: Erc20): Int = decimalsCache.get(token.contractAddress) { decimalsForTokenWithoutCache(token) }

private fun decimalsForTokenWithoutCache(token: Erc20): Int {
    val decimals = token.decimals().send()
    require(decimals < Int.MAX_VALUE.toBigInteger() && decimals > Int.MIN_VALUE.toBigInteger())
    return decimals.toInt()
}
