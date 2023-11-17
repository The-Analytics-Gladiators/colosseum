package io.gladiators.web3

interface AddressBook {
    fun uniswap(): UniswapAddressBook = UniswapAddressBook()
    fun multicall(): String = "0xcA11bde05977b3631167028862bE2a173976CA11"
}

data object DefaultAddressBook : AddressBook
data class UniswapAddressBook(
    val tickLens: String = "0xbfd8137f7d1516D3ea5cA83523914859ec47F573",
    val quoterV2: String = "0x61fFE014bA17989E743c5F6cB21bF9697530B21e", // quote for v2 and v3
    val routerV2: String = "0x68b3465833fb72A70ecDF485E0e4C7bD8665Fc45", // v2 and v3 router
    val positionManager: String = "0xC36442b4a4522E871399CD717aBDD847Ab11FE88",
    val factory: String = "0x1F98431c8aD98523631AE4a59f267346ea31F984"
)
