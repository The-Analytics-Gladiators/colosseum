package io.gladiators.web3

import io.gladiators.chain.BnbTokens
import io.gladiators.chain.MaticTokens
import org.web3j.abi.datatypes.Address
import java.math.BigInteger
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


data class BinanceBlockchain(
    override val blockDuration: Duration = 3.seconds,
    override val minGasPrice: BigInteger = BigInteger("3000000000"),
    override val chain: Chain = Chain.BSC,
    override val nativeTokenWrapper: Address = BnbTokens.Wbnb.address,
) : Blockchain

data class PolygonBlockchain(
    override val blockDuration: Duration = 2.seconds,
    override val minGasPrice: BigInteger = BigInteger("210000000000"),
    override val chain: Chain = Chain.POLYGON,
    override val nativeTokenWrapper: Address = MaticTokens.Wmatic.address,
) : Blockchain

data class ETHBlockchain(
        override val blockDuration: Duration = 14.seconds,
        override val minGasPrice: BigInteger = BigInteger("30000000000"),
        override val chain: Chain = Chain.ETH,
        override val nativeTokenWrapper: Address = Address("0xC02aaA39b223FE8D0A0e5C4F27eAD9083C756Cc2"),
) : Blockchain


data class OptimismBlockchain(
    override val blockDuration: Duration = 2.seconds,
    override val minGasPrice: BigInteger = BigInteger("1000000"),
    override val chain: Chain = Chain.OPTIMIZMIZM,
    override val nativeTokenWrapper: Address = Address("0xtodo"),
) : Blockchain

