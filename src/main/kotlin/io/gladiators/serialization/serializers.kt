package io.gladiators.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.web3j.abi.datatypes.Address
import java.math.BigInteger

typealias BigIntegerJson = @Serializable(with = BigIntegerSerializer::class) BigInteger

object BigIntegerSerializer : KSerializer<BigInteger> {
    override val descriptor = PrimitiveSerialDescriptor("java.math.BigInteger", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): BigInteger =
        decoder.decodeString().toBigInteger()
    override fun serialize(encoder: Encoder, value: BigInteger) =
        encoder.encodeString(value.toString())
}

typealias AddressJson = @Serializable(AddressSerializer::class) Address

class AddressSerializer : KSerializer<Address> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(Address::class.java.canonicalName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Address {
        val string = String.serializer().deserialize(decoder)
        return Address(string)
    }

    override fun serialize(encoder: Encoder, value: Address) {
        String.serializer().serialize(encoder, value.value)
    }

}
