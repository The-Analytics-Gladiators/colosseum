package io.gladiators.web3

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object Web3NodesRepo {
    @Serializable
    data class JsonWeb3Node(val name: String, val chain: String, val url: String)

    private val nodes by lazy {
        (System.getenv()["WEB3_NODES"]
            ?: object {}.javaClass.getResourceAsStream("/web3-nodes.json")?.reader()?.readText()
            ?: object {}.javaClass.getResourceAsStream("/web3-nodes.json.secret")!!.reader().readText())
            .let { Json.decodeFromString<List<JsonWeb3Node>>(it) }
    }

    fun all(chain: Chain): List<JsonWeb3Node> = nodes.filter { it.chain.equals(chain.name, ignoreCase = true) }

    fun eth(name: String): EthNode =
        nodes.find { it.chain.equals(Chain.ETH.name, ignoreCase = true) && name.equals(it.name, ignoreCase = true) }
            ?.let {
                EthNode(it.name, it.url, chain = Chain.ETH)
            }!!
    fun binance(name: String): BinanceNode =
        nodes.find { it.chain.equals(Chain.BSC.name, ignoreCase = true) && name.equals(it.name, ignoreCase = true) }
            ?.let {
                BinanceNode(it.name, it.url, chain = Chain.ETH)
            }!!

    fun optimism(name: String): OptimismNode =
        nodes.find { it.chain.equals(Chain.OPTIMIZMIZM.name, ignoreCase = true) && name.equals(it.name, ignoreCase = true) }
            ?.let {
                OptimismNode(it.name, it.url, chain = Chain.ETH)
            }!!

    fun polygon(name: String): PolygonNode =
        nodes.find { it.chain.equals(Chain.POLYGON.name, ignoreCase = true) && name.equals(it.name, ignoreCase = true) }
            ?.let {
                PolygonNode(it.name, it.url, chain = Chain.ETH)
            }!!
}

