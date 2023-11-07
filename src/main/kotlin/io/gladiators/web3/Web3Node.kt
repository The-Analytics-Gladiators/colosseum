package io.gladiators.web3

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import java.math.BigInteger

sealed interface Web3Node<T : Web3Context, K : AddressBook> {
    val name: String
    val url: String
    val addressBook: K
    fun contextConstructor(web3j: Web3j, credentials:
    Credentials, gasPrice: BigInteger?, gasLimit: BigInteger, blockchain: Blockchain? = null): T
}
