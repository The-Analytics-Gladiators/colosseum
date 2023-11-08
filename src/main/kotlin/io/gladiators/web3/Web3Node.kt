package io.gladiators.web3

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.websocket.WebSocketService
import org.web3j.tx.gas.StaticEIP1559GasProvider
import org.web3j.utils.Async
import java.math.BigInteger
import kotlin.reflect.KClass



interface Web3Node<T: Web3Context> {
    val name: String
    val url: String
    val chain: Chain
    fun buildContext(web3j: Web3j, credentials: Credentials, constants: BlockchainConstants = constantsForChain(chain),
                     gasPrice: BigInteger = constants.minGasPrice, gasLimit: BigInteger = BigInteger("3000000")): T
    companion object {
        fun <T: Web3Context> Web3Node<T>.createWeb3(httpClient: OkHttpClient, pollingInterval: Long = 3000L): Web3j =
            if (url.startsWith("http")) {
                Web3j.build(HttpService(url, httpClient), pollingInterval, Async.defaultExecutorService())
            } else {
                val webSocketService = WebSocketService(url, false)
                webSocketService.connect()
                Web3j.build(webSocketService)
            }
    }
}
data class EthNode(override val name: String, override val url: String, override val chain: Chain = Chain.ETH): Web3Node<ETHContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): ETHContext = ETHContext(
            web3j, Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit)))
}
data class BinanceNode(override val name: String, override val url: String, override val chain: Chain = Chain.BSC): Web3Node<BinanceContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): BinanceContext = BinanceContext(
            web3j, Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit)))
}

data class PolygonNode(override val name: String, override val url: String, override val chain: Chain = Chain.POLYGON): Web3Node<PolygonContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): PolygonContext = PolygonContext(
            web3j, Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit)))
}

data class OptimismNode(override val name: String, override val url: String, override val chain: Chain = Chain.OPTIMIZMIZM): Web3Node<OptimismContext> {
    override fun buildContext(
        web3j: Web3j,
        credentials: Credentials,
        constants: BlockchainConstants,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
    ): OptimismContext = OptimismContext(
        web3j, Web3Defaults(credentials, StaticEIP1559GasProvider(constants.chain.id.toLong(), gasPrice, gasPrice, gasLimit)))
}

data class HardhatNode<out K: Web3Node<T>, T: Web3Context>(val delegate: K): Web3Node<T> by delegate {
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
