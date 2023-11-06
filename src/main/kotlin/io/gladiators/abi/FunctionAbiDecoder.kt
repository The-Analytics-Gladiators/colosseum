package io.gladiators.abi

import com.esaulpaugh.headlong.abi.Function
import com.esaulpaugh.headlong.abi.Tuple
import com.esaulpaugh.headlong.util.FastHex
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

private const val FUNCTION_HASH_SIZE = 8
private const val FUNCTION_HASH_BYTES_SIZE = 4

class FunctionAbiDecoder private constructor(jsonAbiMethods: List<String>) {

    companion object {
        private val jsonMapper = jacksonObjectMapper().registerModule(JavaTimeModule())
        fun forContract(abi: String): FunctionAbiDecoder {
            val tree = jsonMapper.readTree(abi)
            require(tree.isArray)
            val functions = tree.asIterable()
                .filter { it["type"].asText() == "function" }
                .map { it.toString() }
            return FunctionAbiDecoder(functions)
        }

        fun Function.show(params: Tuple): String {
            require(this.inputs.size() == params.size())
            fun showNicely(param: Any): Any = when (param) {
                is Array<*> -> param.map { showNicely(it!!) }
                else -> param
            }
            return this.inputs.zip(params).joinToString(separator = ", ") { (abiType, param) ->
                val paramStr = showNicely(param)
                "$paramStr:${abiType.canonicalType}"
            }
        }
        private fun functionSelectorFromCalldata(calldata: String): String = calldata.removePrefix("0x").take(
            FUNCTION_HASH_SIZE
        )
    }

    val abiFunctions = jsonAbiMethods.map { Function.fromJson(it) }.associateBy { it.selectorHex() }
    fun functionNames(): List<String> = abiFunctions.values.map { it.name }

    fun functionByName(name: String): Function =
        abiFunctions.values.first { it.name == name }

    fun isKnownFunctionCall(calldata: String): Boolean =
        abiFunctions.keys.contains(functionSelectorFromCalldata(calldata))

    data class FunctionCall(val name: String, val params: Tuple)

    fun decodeFunctionCall(calldata: String): FunctionCall {
        val function = abiFunctions[functionSelectorFromCalldata(calldata)]!!
        return FunctionCall(
            function.name, function.decodeCall(
                FastHex.decode(
                    calldata.trim().removePrefix("0x")
                )
            )
        )
    }

    fun decodeFunctionCall(calldata: ByteArray): FunctionCall {
        val function = abiFunctions[FastHex.encodeToString(calldata.take(FUNCTION_HASH_BYTES_SIZE).toByteArray(), 0,
            FUNCTION_HASH_BYTES_SIZE
        )]!!
        return FunctionCall(function.name, function.decodeCall(calldata))
    }
}
