package io.gladiators

import com.esaulpaugh.headlong.util.FastHex
import io.gladiators.solidity.Multicallv3
import io.gladiators.web3.Web3Context
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Type
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.tx.Contract
import org.web3j.tx.gas.StaticGasProvider

data class MulticallDefer(
    val contractAddress: String,
    val functionCall: RemoteFunctionCall<*>,
    val transformation: (List<Type<*>>) -> List<Type<*>>
)

interface CollectCalls {
    fun contract(contract: Contract)
    fun address(address: Address)
    fun call(contract: Contract, call: RemoteFunctionCall<*>, allowFailure: Boolean = false)
    fun call(defer: MulticallDefer, allowFailure: Boolean = false)
    fun call(call: RemoteFunctionCall<*>, allowFailure: Boolean = false)
}

private class CollectCallsInList(
    private var currentContract: Contract? = null,
    private var currentAddress: Address? = null,
    val callsCollected: MutableList<MulticallGateway.MulticallBullet> = mutableListOf()
) : CollectCalls {
    override fun contract(contract: Contract) {
        currentContract = contract
        currentAddress = null
    }

    override fun address(address: Address) {
        currentAddress = address
        currentContract = null
    }

    override fun call(contract: Contract, call: RemoteFunctionCall<*>, allowFailure: Boolean) {
        val readAddress = currentAddress ?: Address(currentContract!!.contractAddress)
        callsCollected.add(
            MulticallGateway.MulticallBullet(readAddress, call, allowFailure)
        )
    }

    override fun call(defer: MulticallDefer, allowFailure: Boolean) {
        callsCollected.add(
            MulticallGateway.MulticallBullet(
                Address(defer.contractAddress),
                defer.functionCall,
                allowFailure,
                defer.transformation
            )
        )
    }

    override fun call(call: RemoteFunctionCall<*>, allowFailure: Boolean) {
        val readAddress = currentAddress ?: Address(currentContract!!.contractAddress)
        callsCollected.add(
            MulticallGateway.MulticallBullet(readAddress, call, allowFailure)
        )
    }
}

fun Multicallv3.multicall(functionCall: CollectCalls.() -> Unit): List<List<Type<*>>> {
    val container = CollectCallsInList()
    container.let(functionCall)
    return MulticallGateway.multicall(this, container.callsCollected)
}

fun Web3Context.multicall(
    address: Address = Address(addressBook.multicall()),
    functionCall: CollectCalls.() -> Unit
): List<List<Type<*>>> =
    multicallv3(address, web3j).multicall(functionCall)

internal object MulticallGateway {
    internal class MulticallBullet internal constructor(
        val address: Address,
        val remoteFunction: RemoteFunctionCall<*>,
        val allowFailure: Boolean,
        val transform: (List<Type<*>>) -> List<Type<*>> = { it },
    )

    internal fun call(contract: Contract, remote: RemoteFunctionCall<*>, allowFailure: Boolean = false): MulticallBullet =
        call(Address(contract.contractAddress), remote, allowFailure)

    internal fun call(address: Address, remoteFunction: RemoteFunctionCall<*>, allowFailure: Boolean = false): MulticallBullet =
        MulticallBullet(address, remoteFunction, allowFailure)


    /**
     * Only read requests can be pipelined
     */
    internal fun multicall(
        web3j: Web3j,
        bullets: List<MulticallBullet>,
        address: Address
    ): List<List<Type<*>>> {
        return multicall(multicallv3(address, web3j), bullets)
    }

    internal fun multicall(multicaller: Multicallv3, bullets: List<MulticallBullet>): List<List<Type<*>>> {
        val calls = bullets.map { bullet ->
            val calldata = FastHex.decode(bullet.remoteFunction.encodeFunctionCall().removePrefix("0x"))
            Multicallv3.Call3(bullet.address.value, bullet.allowFailure, calldata)
        }
        val responses = multicaller.aggregate3(calls.toMutableList()).send() as List<Multicallv3.Result>
        return responses.zip(bullets).map { (response, bullet) ->
            if (response.success) {
                val decoded = bullet.remoteFunction.decodeFunctionResponse(
                    FastHex.encodeToString(
                        response.returnData,
                        0,
                        response.returnData.size
                    )
                )
                bullet.transform(decoded)
            } else {
                emptyList()
            }
        }
    }

}

private fun multicallv3(
    address: Address,
    web3j: Web3j
): Multicallv3 {
    val nopCred = Credentials.create("1100")
    val nopGasProvider = StaticGasProvider(1.toBigInteger(), 1.toBigInteger())
    return Multicallv3.load(address.value, web3j, nopCred, nopGasProvider)
}
