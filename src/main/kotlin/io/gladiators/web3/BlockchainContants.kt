package io.gladiators.web3

import io.gladiators.chain.BnbTokens
import io.gladiators.chain.MaticTokens
import io.gladiators.serialization.AddressJson
import io.gladiators.serialization.BigIntegerJson
import io.gladiators.serialization.BigIntegerSerializer
import kotlinx.serialization.Serializable
import org.web3j.abi.datatypes.Address
import org.web3j.utils.Convert
import java.math.BigInteger
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


@Serializable
enum class Chain(val id: Int) {
    ETH(1),
    BSC(56),
    POLYGON(137),
    OPTIMISM(10),
    ARBITRUM_ONE(42161),
    HARDHAT(31337)
}

@Serializable
data class BlockchainConstants(
    val blockDuration: Duration,
    val minGasPrice: BigIntegerJson,
    val chain: Chain,
    val nativeTokenWrapper: AddressJson
)

fun constantsForChain(chain: Chain): BlockchainConstants =
    when (chain) {
        Chain.ETH -> BlockchainConstants(
            blockDuration = 14.seconds,
            minGasPrice = BigInteger("10000000000"),
            chain = Chain.ETH,
            nativeTokenWrapper = Address("0xC02aaA39b223FE8D0A0e5C4F27eAD9083C756Cc2")
        )

        Chain.BSC -> BlockchainConstants(
            blockDuration = 3.seconds,
            minGasPrice = BigInteger("1000000000"),
            chain = Chain.BSC,
            nativeTokenWrapper = BnbTokens.WBNB.address
        )

        Chain.POLYGON -> BlockchainConstants(
            blockDuration = 2.seconds,
            minGasPrice = BigInteger("210000000000"),
            chain = Chain.POLYGON,
            nativeTokenWrapper = MaticTokens.WMATIC.address
        )

        Chain.OPTIMISM -> BlockchainConstants(
            blockDuration = 2.seconds,
            minGasPrice = Convert.toWei("0.011", Convert.Unit.GWEI).toBigInteger(),
            chain = Chain.OPTIMISM,
            nativeTokenWrapper = Address("0x4200000000000000000000000000000000000042")
        )

        Chain.ARBITRUM_ONE -> BlockchainConstants(
            blockDuration = 0.3.seconds,
            minGasPrice = Convert.toWei("0.3", Convert.Unit.GWEI).toBigInteger(),
            chain = Chain.ARBITRUM_ONE,
            nativeTokenWrapper = Address("0x912CE59144191C1204E64559FE8253a0e49E6548")
        )

        Chain.HARDHAT -> BlockchainConstants(
            blockDuration = 1.seconds,
            minGasPrice = BigInteger("1000000000"),
            chain = Chain.HARDHAT,
            nativeTokenWrapper = BnbTokens.WBNB.address
        )
    }
