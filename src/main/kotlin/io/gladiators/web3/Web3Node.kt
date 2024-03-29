package io.gladiators.web3

import okhttp3.OkHttpClient
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.tx.gas.StaticEIP1559GasProvider
import org.web3j.utils.Async
import java.math.BigInteger
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService


interface Web3Node<T : Web3Context> {
    val name: String
    val url: String
    val chain: Chain
    fun buildContext(
        web3j: Web3j, credentials: Credentials, constants: BlockchainConstants = constantsForChain(chain),
        gasPrice: BigInteger = constants.minGasPrice, gasLimit: BigInteger = BigInteger("3000000")
    ): T

    companion object {

        private fun daemonPool(count: Int = Runtime.getRuntime().availableProcessors()): ScheduledExecutorService {
            return Executors.newScheduledThreadPool(count) { runnable ->
                val thread = Thread(runnable)
                thread.isDaemon = true
                thread
            }
        }
        fun createWeb3(url: String, httpClient: OkHttpClient, pollingInterval: Long = 3000L, asyncPool: ScheduledExecutorService = daemonPool()): Web3j =
            if (url.startsWith("http")) {
                Web3j.build(HttpService(url, httpClient), pollingInterval, asyncPool)
            } else {
                val webSocketService = WebSocketService(url, false)
                webSocketService.connect()
                Web3j.build(webSocketService)
            }
    }
}

data class EthNode(override val name: String, override val url: String, override val chain: Chain = Chain.ETH) :
    Web3Node<EthereumContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): EthereumContext = ETHContextContainer(
        web3j,
        Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit))
    )
}

data class BinanceNode(override val name: String, override val url: String, override val chain: Chain = Chain.BSC) :
    Web3Node<BinanceContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): BinanceContext = BinanceContextContainer(
        web3j,
        Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit))
    )
}

data class PolygonNode(override val name: String, override val url: String, override val chain: Chain = Chain.POLYGON) :
    Web3Node<PolygonContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): PolygonContext = PolygonContextContainer(
        web3j,
        Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit))
    )
}

data class OptimismNode(
    override val name: String,
    override val url: String,
    override val chain: Chain = Chain.OPTIMISM
) : Web3Node<OptimismContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): OptimismContext = OptimismContextContainer(
        web3j,
        Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit))
    )
}

data class ArbitrumNode(
    override val name: String,
    override val url: String,
    override val chain: Chain = Chain.ARBITRUM_ONE
) : Web3Node<ArbitrumContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): ArbitrumContext = ArbitrumContextContainer(
        web3j,
        Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit))
    )
}

data class HardhatNode<out K : Web3Node<T>, T : Web3Context>(val delegate: K) : Web3Node<T> by delegate {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): T {
        val constantsRefined = constants.copy(chain = Chain.HARDHAT)
        return delegate.buildContext(web3j, credentials, constantsRefined, gasPrice, gasLimit)
    }
}
