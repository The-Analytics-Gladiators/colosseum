package io.gladiators.cex

import io.gladiators.chain.BnbTokens
import io.gladiators.chain.MaticTokens
import io.gladiators.chain.erc20
import io.gladiators.web3.Web3NodesRepo
import io.gladiators.web3.withReadonlyWeb3Context
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import java.math.RoundingMode

class CexQuotesKtTest : FunSpec({

    withData(
        1000 to BnbTokens.CAKE,
        3000 to BnbTokens.WETH
    ) { (fiat, tokenAddr) ->
        withReadonlyWeb3Context(Web3NodesRepo.binance("BlastApi")) {
            val oneWay = usdToToken(tokenAddr.address.value, fiat.toBigDecimal())
            val andBack = tokenToUSD(erc20(tokenAddr.address), oneWay)
            fiat shouldBe andBack.setScale(0, RoundingMode.HALF_DOWN).toInt()
        }
    }

    test("other methods") {
        withReadonlyWeb3Context(Web3NodesRepo.binance("BlastApi")) {
            usdToToken(BnbTokens.WBNB.address.value, bnbToUsd(1000.toBigInteger())).toInt() shouldBe 1000
            val bnbInEth =
                tokenToToken(erc20(BnbTokens.WBNB.address), 10000000.toBigInteger(), erc20(BnbTokens.WETH.address))
            val andBack = tokenToToken(erc20(BnbTokens.WETH.address), bnbInEth, erc20(BnbTokens.WBNB.address)).toInt()
            andBack shouldBeGreaterThan 10000000 - 10
            andBack shouldBeLessThan 10000000 + 10
        }
    }
    test("other chains") {
        withReadonlyWeb3Context(Web3NodesRepo.polygon("Quicknode")) {
            usdToToken(
                MaticTokens.WMATIC.address.value,
                tokenToUSD(MaticTokens.WMATIC.address.value, 1000.toBigInteger())
            ).toInt() shouldBe 1000
            val maticInEth = tokenToToken(
                erc20(MaticTokens.WMATIC.address),
                10000000.toBigInteger(),
                erc20(MaticTokens.WETH.address)
            )
            val andBack =
                tokenToToken(erc20(MaticTokens.WETH.address), maticInEth, erc20(MaticTokens.WMATIC.address)).toInt()
            andBack shouldBeGreaterThan (10000000 - (10000000 * 0.001)).toInt()
            andBack shouldBeLessThan (10000000 + (10000000 * 0.001)).toInt()
        }
    }
})
