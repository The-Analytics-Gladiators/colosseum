package io.gladiators.web3

import org.web3j.abi.datatypes.Address
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger
import kotlin.time.Duration

enum class Chain(val id: Int) {
    ETH(1),
    BSC(56),
    POLYGON(137),
    OPTIMIZMIZM(10),
    HARDHAT(31337)
}
sealed interface Blockchain {
    val blockDuration: Duration
    val minGasPrice: BigInteger
    val chain: Chain
    val nativeTokenWrapper: Address
}
data class Web3Defaults(val credentials: Credentials, val gasProvider: ContractGasProvider)

interface Web3Context {
    val web3j: Web3j
    val web3Defaults: Web3Defaults
    val blockchainConstants: Blockchain
}
