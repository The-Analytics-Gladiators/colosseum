package io.gladiators.web3

import io.github.oshai.kotlinlogging.KotlinLogging
import io.gladiators.chain.erc20
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.TransactionEncoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.EthFilter
import org.web3j.protocol.core.methods.response.*
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.utils.Async
import org.web3j.utils.Numeric
import java.math.BigInteger
import kotlin.time.measureTime

private val logger = KotlinLogging.logger {  }
fun org.web3j.protocol.websocket.events.Log.toCoreLog(): Log =
    Log(
        false,
        this.logIndex,
        this.transactionIndex,
        this.transactionHash,
        this.blockHash,
        this.blockNumber,
        this.address,
        this.data,
        "no",
        this.topics
    )


fun Web3Context.sign(tx: RawTransaction): String =
    Numeric.toHexString(TransactionEncoder.signMessage(tx, web3Defaults.credentials))

fun Web3Context.primaryAddressNonce(): BigInteger {
    val ethGetTransactionCount: EthGetTransactionCount = web3j.ethGetTransactionCount(
        web3Defaults.credentials.address,
        DefaultBlockParameterName.PENDING
    ).send()
    return ethGetTransactionCount.transactionCount
}

fun Web3Context.loadTransactionReceipt(transactionHash: String): TransactionReceipt? {
    val transactionReceipt: EthGetTransactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send()
    return transactionReceipt.transactionReceipt.orElse(null)
}
suspend fun Web3Context.waitForReceipt(txHash: String, maxBlocks: Int = 3): TransactionReceipt? =
    (1..maxBlocks).firstNotNullOfOrNull {
        val receipt = loadTransactionReceipt(txHash)
        if (receipt == null) {
            delay(blockchainConstants.blockDuration)
        }
        receipt
    }
fun Web3Context.isNodeLagging(latestBlockEtherscan: BigInteger, allowedLag: Int): BigInteger? {
    return try {
        val latestBlockNumber = web3j.ethBlockNumber().send().blockNumber
        // smoketest
        val latestBlockObj = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send()
        val diff = latestBlockEtherscan - latestBlockNumber
        val isSyncing = web3j.ethSyncing().send().isSyncing
        val lastBlockLogs = web3j.ethGetLogs(EthFilter(latestBlockObj.block.hash)).send().logs
        if (diff > allowedLag.toBigInteger() || lastBlockLogs.isEmpty() || isSyncing) {
            null
        } else {
            latestBlockNumber
        }
    } catch (ex: Exception) {
        logger.debug(ex) { "Node $web3j is not available $ex" }
        null
    }
}

fun web3For(node: String, httpClient: OkHttpClient, pollingInterval: Long): Web3j =
    if (node.startsWith("http")) {
        Web3j.build(HttpService(node, httpClient), pollingInterval, Async.defaultExecutorService())
    } else {
        val webSocketService = WebSocketService(node, false)
        webSocketService.connect()
        Web3j.build(webSocketService)
    }

fun Web3j.ping(iterations: Int = 2): kotlin.time.Duration {
    val web3j = this
    web3j.ethBlockNumber() // warmup
    return (0 until iterations).map {
        measureTime {
            web3j.ethBlockNumber()
        }
    }.reduce { one, another -> one.plus(another) } / iterations
}
fun Web3Context.sendFunds(
    amount: BigInteger,
    recipient: String,
    gasPrice: BigInteger,
    nonce: BigInteger,
    gasLimit: BigInteger = 80000.toBigInteger(),
): EthSendTransaction = web3j.ethSendRawTransaction(sign(sendFundsTx(amount, recipient, gasPrice, nonce, gasLimit))).send()

fun Web3Context.sendFundsTx(
    amount: BigInteger,
    recipient: String,
    gasPrice: BigInteger,
    nonce: BigInteger,
    gasLimit: BigInteger = 80000.toBigInteger(),
): RawTransaction =
    RawTransaction.createTransaction(
        blockchainConstants.chain.id.toLong(),
        nonce,
        gasPrice,
        gasLimit,
        recipient,
        amount,
        "",
        emptyList()
    )
fun Web3Context.approveTokens(
    spender: String,
    tokens: List<String>,
    limit: BigInteger = BigInteger("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16)
): Boolean {
    return tokens.all {
        erc20(it).approve(
            spender,
            limit
        ).send().isStatusOK
    }
}
