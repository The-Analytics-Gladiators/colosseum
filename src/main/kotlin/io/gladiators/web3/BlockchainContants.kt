package io.gladiators.web3

import io.gladiators.chain.BnbTokens
import io.gladiators.chain.MaticTokens
import org.web3j.abi.datatypes.Address
import java.math.BigInteger
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

enum class Chain(val id: Int) {
    ETH(1),
    BSC(56),
    POLYGON(137),
    OPTIMIZMIZM(10),
    HARDHAT(31337)
}
data class BlockchainConstants(
    val blockDuration: Duration,
    val minGasPrice: BigInteger,
    val chain: Chain,
    val nativeTokenWrapper: Address
)

fun constantsForChain(chain: Chain): BlockchainConstants =
    when (chain) {
        Chain.ETH -> BlockchainConstants(
            blockDuration = 14.seconds,
            minGasPrice = BigInteger("3000000000"),
            chain = Chain.ETH,
            nativeTokenWrapper = Address("0xC02aaA39b223FE8D0A0e5C4F27eAD9083C756Cc2")
        )
        Chain.BSC -> BlockchainConstants(
            blockDuration = 3.seconds,
            minGasPrice = BigInteger("3000000000"),
            chain = Chain.BSC,
            nativeTokenWrapper = BnbTokens.Wbnb.address
        )
        Chain.POLYGON -> BlockchainConstants(
            blockDuration = 2.seconds,
            minGasPrice = BigInteger("210000000000"),
            chain = Chain.POLYGON,
            nativeTokenWrapper = MaticTokens.Wmatic.address
        )
        Chain.OPTIMIZMIZM -> throw IllegalArgumentException("todo") // todo
        Chain.HARDHAT -> BlockchainConstants(
            blockDuration = 1.seconds,
            minGasPrice = BigInteger("1000000000"),
            chain = Chain.HARDHAT,
            nativeTokenWrapper = BnbTokens.Wbnb.address
        )
    }
