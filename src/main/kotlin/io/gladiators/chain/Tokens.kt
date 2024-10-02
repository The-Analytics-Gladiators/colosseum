package io.gladiators.chain

import org.web3j.abi.datatypes.Address

sealed interface ChainToken {
    val address: Address
}

enum class BnbTokens(override val address: Address): ChainToken {
    WBNB(Address("0xbb4CdB9CBd36B01bD1cBaEBF2De08d9173bc095c")),
    BUSD(Address("0xe9e7CEA3DedcA5984780Bafc599bD69ADd087D56")),
    USDT(Address("0x55d398326f99059fF775485246999027B3197955")),
    BTCB(Address("0x7130d2A12B9BCbFAe4f2634d864A1Ee1Ce3Ead9c")),
    WETH(Address("0x2170ed0880ac9a755fd29b2688956bd959f933f8")),
    CAKE(Address("0x0E09FaBB73Bd3Ade0a17ECC321fD13a19e81cE82")),
    USDC(Address("0x8ac76a51cc950d9822d68b83fe1ad97b32cd580d"))
}

enum class MaticTokens(override val address: Address): ChainToken {
    WMATIC(Address("0x0d500B1d8E8eF31E21C99d1Db9A6444d3ADf1270")),
    USDC(Address("0x2791Bca1f2de4661ED88A30C99A7a9449Aa84174")),
    USDT(Address("0xc2132D05D31c914a87C6611C10748AEb04B58e8F")),
    WETH(Address("0x7ceB23fD6bC0adD59E62ac25578270cFf1b9f619"))
}


