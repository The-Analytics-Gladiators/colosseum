package io.gladiators.web3

import io.gladiators.web3.Web3Node.Companion.createWeb3
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.tx.gas.StaticEIP1559GasProvider
import java.math.BigInteger
import java.util.concurrent.TimeUnit


@JvmInline
value class WalletAccount(val accountSeed: Int) {
    companion object {
        val primary = WalletAccount(0)
        val secondary = WalletAccount(1)
        val canary = WalletAccount(2)
        val readonly = WalletAccount(-1)
        val hardhat =  WalletAccount(-2)
        val hardhat2 =  WalletAccount(-3)
        val hardhat3 =  WalletAccount(-4)
    }
}

private val fastPaceHttp: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .connectTimeout(java.time.Duration.ofMillis(500))
        .readTimeout(java.time.Duration.ofMillis(1000))
        .writeTimeout(java.time.Duration.ofMillis(500))
        .connectionPool(ConnectionPool(10, 60L, TimeUnit.SECONDS))
        .build()

}
private val slowPaceHttp: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .connectTimeout(java.time.Duration.ofSeconds(1))
        .readTimeout(java.time.Duration.ofSeconds(40))
        .writeTimeout(java.time.Duration.ofSeconds(1))
        .build()
}

data class Gas(val price: BigInteger, val limits: BigInteger = BigInteger("3000000"))

data class Web3Defaults(val credentials: Credentials, val gasProvider: ContractGasProvider)

interface Web3Context {
    val web3j: Web3j
    val web3Defaults: Web3Defaults
    val blockchainConstants: BlockchainConstants
    val addressBook: AddressBook
}
data class UnknownWeb3Context(
    override val web3j: Web3j,
    override val web3Defaults: Web3Defaults,
    override val blockchainConstants: BlockchainConstants,
    override val addressBook: AddressBook = DefaultAddressBook
) : Web3Context

interface ETHContext: Web3Context

data class ETHContextContainer(
    override val web3j: Web3j,
    override val web3Defaults: Web3Defaults,
    override val blockchainConstants: BlockchainConstants = constantsForChain(Chain.ETH),
    override val addressBook: AddressBook = DefaultAddressBook
) : ETHContext

interface OptimismContext: Web3Context

data class OptimismContextContainer(
    override val web3j: Web3j,
    override val web3Defaults: Web3Defaults,
    override val blockchainConstants: BlockchainConstants = constantsForChain(Chain.OPTIMIZMIZM),
    override val addressBook: AddressBook = DefaultAddressBook
) : OptimismContext

interface PolygonContext: Web3Context
data class PolygonContextContainer(
    override val web3j: Web3j,
    override val web3Defaults: Web3Defaults,
    override val blockchainConstants: BlockchainConstants = constantsForChain(Chain.POLYGON),
    override val addressBook: AddressBook = DefaultAddressBook
) : PolygonContext

interface BinanceContext: Web3Context
data class BinanceContextContainer(
    override val web3j: Web3j,
    override val web3Defaults: Web3Defaults,
    override val blockchainConstants: BlockchainConstants = constantsForChain(Chain.BSC),
    override val addressBook: AddressBook = DefaultAddressBook
) : BinanceContext
fun <T> withWeb3Context(
    web3j: Web3j,
    constants: BlockchainConstants,
    account: WalletAccount = WalletAccount.primary,
    credentials: Credentials = loadCredentials(account),
    gasPrice: BigInteger = web3j.ethGasPrice().send().gasPrice,
    gasLimit: BigInteger = BigInteger("400000"),
    function: Web3Context.() -> T
): T = UnknownWeb3Context(web3j, Web3Defaults(credentials,
        StaticEIP1559GasProvider(
            constants.chain.id.toLong(),
            gasPrice,
            gasPrice,
            gasLimit)), constants).let(function)
fun <T, K : Web3Context> withWeb3Context(
    web3Node: Web3Node<K>,
    credentials: Credentials,
    gas: Gas = Gas(BigInteger.ZERO), // will be set automatically or may be overwritten
    httpClient: OkHttpClient = fastPaceHttp,
    function: K.() -> T
): T {
    val web3j = createWeb3(web3Node.url, httpClient)
    val context = if(gas.price == BigInteger.ZERO) {
        web3Node.buildContext(web3j, credentials)
    } else {
        web3Node.buildContext(web3j, credentials, constantsForChain(web3Node.chain), gas.price, gas.limits)
    }
    return context.let(function)
}

fun <T, K : Web3Context> withWeb3Context(
    web3Node: Web3Node<K>,
    account: WalletAccount = WalletAccount.primary,
    gasPrice: BigInteger? = null, // sets automatically
    gasLimit: BigInteger = BigInteger("600000"),
    httpClient: OkHttpClient = fastPaceHttp,
    function: K.() -> T
): T = withWeb3Context(web3Node, account, gasPrice, gasLimit, httpClient, function)

fun <T, K : Web3Context> withReadonlyWeb3Context(
    web3Node: Web3Node<K>,
    httpClient: OkHttpClient = slowPaceHttp,
    function: K.() -> T
): T {
    return withWeb3Context(
        web3Node = web3Node,
        credentials = loadCredentials(WalletAccount.readonly, ""),
        httpClient = httpClient,
        function = function
    )
}

fun resolveWallet(file: String = "/secrets/metamask.secret"): String = try {
    System.getenv()["WALLET"] ?: object {}.javaClass.getResourceAsStream(file)!!.bufferedReader().readText().trim()
} catch (ex: NullPointerException) {
    throw IllegalArgumentException("Wallet file not found")
}


private fun loadCredentials(account: WalletAccount, wallet: String = resolveWallet()): Credentials {
    return when (account) {
        WalletAccount.readonly -> {
            Credentials.create("0x00000000")
        }
        WalletAccount.hardhat -> {
            Credentials.create("0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80")
        }
        WalletAccount.hardhat2 -> {
            Credentials.create("0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d")
        }
        WalletAccount.hardhat3 -> {
            Credentials.create("0x5de4111afa1a4b94908f83103eb1f1706367c2e68ca870fc3fb9a804cdab365a")
        }
        else -> {
            WalletUtils.loadBip39Credentials(null, wallet)
            val masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(wallet, null))
            val path = arrayOf(
                44.or(Bip32ECKeyPair.HARDENED_BIT),
                60.or(Bip32ECKeyPair.HARDENED_BIT),
                0.or(Bip32ECKeyPair.HARDENED_BIT),
                0,
                account.accountSeed
            )
            val x = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path.toIntArray())
            Credentials.create(x)
        }
    }

}

