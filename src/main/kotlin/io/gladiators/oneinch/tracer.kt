@file:UseSerializers(BigIntAsHexSerializer::class)

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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.runInterruptible
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigInteger
import kotlin.time.Duration.Companion.seconds


@Serializable
private data class OneInchTracesResponse(val transactionTrace: TransactionTrace, val type: String)

@Serializable
data class TransactionTrace(
    val from: String,
    val to: String,
    val nonce: BigInteger,
    val txHash: String,
    val value: BigInteger,
    val type: CallType,
    val gasUsed: String,
    val gasPrice: BigInteger,
    val gasLimit: Int,
    val gasHex: String,
    val intrinsicGas: Int,
    val gasRefund: Int,
    val gasActual: Int,
    val input: String,
    val status: String,
    val calls: List<TransactionCall>,
    val logs: List<LogRecord>,
    val storage: List<StorageAction>,
    val depth: Int
)

enum class CallStatus {
    RETURNED, REVERTED, STOPPED
}

@Serializable
data class TransactionCall(
    val type: CallType, // [ SELFDESTRUCT, CREATE, CREATE2, CALL, CALLCODE, DELEGATECALL, STATICCALL, TRANSFER_ETHER, INVALID, STOP ]
    val from: String, //example: 0x71dad91e069861350c2ba882fd86762a5efe8792
    val to: String,
    val value: String = "",
    val gas: BigInteger,
    val gasUsed: Int,
    val gasLimit: Int,
    val input: String,
    val prevGasLimit: String,
    val gasCost: String,
    val status: CallStatus,
    val success: Int,
    val res: String = "",
    val depth: Int,
    val output: String = "",
    val error: String = "",
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
enum class StorageActionType {
    @SerialName("store")
    STORE,

    @SerialName("load")
    LOAD
}

@Serializable
data class StorageAction(
    val type: StorageActionType,
    val key: String, // slot
    val value: String
)

interface OneInchContext {
    fun txTrace(blockNumber: BigInteger, tx: String): TransactionTrace
}

private fun resolveToken(): String = try {
    System.getenv("ONE_INCH_TOKEN") ?: object {}.javaClass.getResourceAsStream("/one-inch-token.secret")!!
        .bufferedReader().readText().trim()
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


enum class CallType {
    SELFDESTRUCT, CREATE, CREATE2, CALL, CALLCODE, DELEGATECALL, STATICCALL, TRANSFER_ETHER, INVALID, STOP
}

fun <T> withOneInchSync(
    chain: Chain,
    apiKey: String = resolveToken(),
    client: HttpClient = defaultHttpClient(apiKey),
    block: OneInchContext.() -> T
) = runBlocking {
    withOneInch(chain, apiKey, client, block = block)
}

suspend fun <T> withOneInch(
    chain: Chain,
    apiKey: String = resolveToken(),
    client: HttpClient = defaultHttpClient(apiKey),
    block: suspend OneInchContext.() -> T
) {
    val context = currentCoroutineContext()
    client.use { httpClient ->
        block(object : OneInchContext {
            override fun txTrace(blockNumber: BigInteger, tx: String): TransactionTrace {
                return runBlocking(context) {
                    val result =
                        httpClient.get("https://api.1inch.dev/traces/v1.0/chain/${chain.id}/block-trace/$blockNumber/tx-hash/$tx")
                    require(result.status.isSuccess()) { "Failed to get transaction trace ${result.body<String>()}" }
                    val decoded: OneInchTracesResponse = result.body()
                    decoded.transactionTrace
                }
            }
        })
    }
}

object BigIntAsHexSerializer : KSerializer<BigInteger> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BigInt", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: BigInteger) = encoder.encodeString("0x" + value.toString(16))
    override fun deserialize(decoder: Decoder): BigInteger = BigInteger(decoder.decodeString().removePrefix("0x"), 16)
}
