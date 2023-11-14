package io.gladiators.chain

import io.gladiators.solidity.Bep20
import io.gladiators.solidity.Erc20
import io.gladiators.web3.Web3Context
import org.web3j.abi.datatypes.Address
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.tx.Contract
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.gas.StaticGasProvider
import java.math.BigInteger

private val nopCred = Credentials.create("1100")
private val nopGasProvider = StaticGasProvider(1.toBigInteger(), 1.toBigInteger())
fun Web3Context.tokenNameByAddress(address: Address): String = tokenNameByAddress(this.web3j, address)
internal fun Web3Context.tokenNameByAddress(web3j: Web3j, address: Address): String {
    val ercContract = Erc20.load(address.value, web3j, nopCred, nopGasProvider)
    return ercContract.name().send()
}

fun Web3Context.myTokenBalance(token: Address): BigInteger {
    val ercContract =
        Erc20.load(token.value, web3j, nopCred, nopGasProvider)
    return ercContract.balanceOf(web3Defaults.credentials.address).send()
}

fun Web3Context.tokenBalance(owner: Contract, token: String): BigInteger =
    tokenBalance(Address(owner.contractAddress), token)

fun Web3Context.tokenBalance(owner: Address, token: Address): BigInteger =
    tokenBalance(owner, token.value)
fun Web3Context.tokenBalance(owner: Address, token: String): BigInteger {
    val ercContract = Erc20.load(token, web3j, nopCred, nopGasProvider)
    return ercContract.balanceOf(owner.value).send()
}

fun Web3Context.myTokenBalance(token: Erc20): BigInteger = myTokenBalance(Address(token.contractAddress))
fun Web3Context.myTokenBalance(token: Bep20): BigInteger = myTokenBalance(Address(token.contractAddress))

fun Web3Context.erc20(address: String): Erc20 =
    Erc20.load(address, web3j,
        RawTransactionManager(web3j, web3Defaults.credentials, blockchainConstants.chain.id.toLong()),
        web3Defaults.gasProvider)
fun Web3Context.erc20(address: Address): Erc20 = erc20(address.value)
