package io.gladiators.oneinch

import io.gladiators.web3.Chain
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import java.math.BigInteger
import kotlin.time.Duration.Companion.seconds

@Serializable
private data class OneInchTracesResponse(val transactionTrace: TransactionTrace, val type: String)
@Serializable
data class TransactionTrace(
    val from: String,
    val to: String,
    val nonce: String,
    val txHash: String,
    val value: String,
    val type: String,
    val gasUsed: String,
    val gasPrice: String,
    val gasLimit: String,
    val gasHex: String,
    val intrinsicGas: String,
    val gasRefund: String,
    val gasActual: String,
    val input: String,
    val status: String,
    val calls: List<TransactionCall>,
    val logs: List<LogRecord>,
    val storage: List<StorageAction>,
    val depth: Int
)

@Serializable
data class TransactionCall(
    val type: String, // [ SELFDESTRUCT, CREATE, CREATE2, CALL, CALLCODE, DELEGATECALL, STATICCALL, TRANSFER_ETHER, INVALID, STOP ]
    val from:	String, //example: 0x71dad91e069861350c2ba882fd86762a5efe8792
    val to:	String,
    val value:	String = "",
    val gas:	String,
    val gasUsed: String,
    val gasLimit: String,
    val input:	String,
    val prevGasLimit: String,
    val gasCost: String,
    val status: String,
    val success: Int,
    val res: String = "",
    val depth: Int,
    val output:	String = "",
    val error:	String = "",
    val storage: List<StorageAction>,
    val calls: List<TransactionCall>,
    val logs: List<LogRecord>
)

@Serializable
data class LogRecord(
    val topics: List<String>,
    val contract: String,
    val data: String
)
@Serializable
data class StorageAction(
    val type: String,
    val key: String,
    val value: String
)

interface OneInchTracerContext {
    fun txTrace(blockNumber: BigInteger, tx: String): TransactionTrace
}
private fun resolveToken(): String = try {
    System.getenv("ONE_INCH_TOKEN") ?: object {}.javaClass.getResourceAsStream("/one-inch-token.secret")!!.bufferedReader().readText().trim()
} catch (ex: NullPointerException) {
    throw IllegalArgumentException("Token file not found")
}

private fun defaultHttpClient(apiKey: String): HttpClient = HttpClient(CIO) {
    followRedirects = true
    expectSuccess = true
    install(HttpTimeout) {
        connectTimeoutMillis = 1.seconds.inWholeMilliseconds
        requestTimeoutMillis = 5.seconds.inWholeMilliseconds
        socketTimeoutMillis = 1.seconds.inWholeMilliseconds
    }
    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens(apiKey, "xyz111")
            }
        }
    }
    install(ContentNegotiation) {
        json()
    }
    defaultRequest {
        host = "api.1inch.dev"
        header("accept", "application/json")
    }
}

fun <T> withOneInchTracer(chain: Chain,
                          apiKey: String = resolveToken(),
                          client: HttpClient = defaultHttpClient(apiKey),
                          block: OneInchTracerContext.() -> T) {
    block(object: OneInchTracerContext {
        override fun txTrace(blockNumber: BigInteger, tx: String): TransactionTrace {
            return runBlocking {
                val result = client.get("https://api.1inch.dev/traces/v1.0/chain/${chain.id}/block-trace/$blockNumber/tx-hash/$tx")
                require(result.status.isSuccess()) { "Failed to get transaction trace ${result.body<String>()}"}
                val decoded: OneInchTracesResponse = result.body()
                decoded.transactionTrace
            }
        }
    })
}