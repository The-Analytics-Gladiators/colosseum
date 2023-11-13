package io.gladiators.cex

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.benmanes.caffeine.cache.Caffeine
import io.gladiators.chain.BnbTokens
import io.gladiators.chain.erc20
import io.gladiators.solidity.Erc20
import io.gladiators.web3.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.web3j.abi.datatypes.Address
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode
import java.time.Duration

private data class TokenCacheId(val blockchain: String, val address: String)

private val priceCache = Caffeine.newBuilder()
    .expireAfterWrite(Duration.ofHours(1))
    .maximumSize(1000)
    .build<TokenCacheId, Double> { key ->
        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(5))
            .connectTimeout(Duration.ofSeconds(5))
            .readTimeout(Duration.ofSeconds(5))
            .writeTimeout(Duration.ofSeconds(5))
            .build()
        val chain = key.blockchain
        val address = key.address
        val response = httpClient.newCall(
            Request.Builder()
                .url("""https://api.coingecko.com/api/v3/simple/token_price/$chain?contract_addresses=${address}&vs_currencies=usd""".trimMargin())
                .header("accept", "application/json")
                .build()
        ).execute()
        require(response.isSuccessful)
        val body = response.body!!.string()
        httpClient.connectionPool.evictAll()
        try {
            val price = ObjectMapper().readTree(body)[address]["usd"].asDouble()
            require(price > 0)
            price
        } catch (ex: Exception){
          throw IllegalArgumentException("No cex quote for $address", ex)
        }
    }

private fun chainToGeckoName(context: Web3Context): String = when(context.blockchainConstants.chain) {
    Chain.POLYGON -> "polygon-pos"
    Chain.BSC -> "binance-smart-chain"
    Chain.OPTIMIZMIZM -> "optimistic-ethereum"
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
    usdToToken(erc20(Address(token)), amountWei, chainToGeckoName(this))

fun usdToToken( to: Erc20, amountWei: BigDecimal, chain: String): BigInteger {
    val tokenPrice = usdForOneToken(to, chain)
    val weiInOneToken = BigInteger.valueOf(10).pow(decimalsForToken(to)).toBigDecimal()
    return weiInOneToken.multiply(amountWei).divide(tokenPrice.toBigDecimal(), MathContext(15))
        .setScale(0, RoundingMode.FLOOR)
        .toBigInteger()
}

fun Web3Context.tokenToUSD(
    token: String, amountWei: BigInteger, mathContext: MathContext = MathContext(15)
): BigDecimal {
    val tokenPrice = usdForOneToken(token)
    val weiCount = BigInteger.valueOf(10).pow(decimalsForToken(erc20(Address(token))))
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
    tokenToUSD(erc20(BnbTokens.Wbnb.address), amountWei, chainToGeckoName(this))

private fun decimalsForToken(token: Erc20): Int = decimalsCache.get(token.contractAddress) {
    val decimals = token.decimals().send()
    require(decimals < Int.MAX_VALUE.toBigInteger() && decimals > Int.MIN_VALUE.toBigInteger())
    decimals.toInt()
}
