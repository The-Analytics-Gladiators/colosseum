package io.gladiators

import io.gladiators.web3.Web3NodesRepo
import io.gladiators.web3.withReadonlyWeb3Context
import io.kotest.core.spec.style.FunSpec

class Web3ContextKtTest : FunSpec({

    test("readonly creds") {
        withReadonlyWeb3Context(Web3NodesRepo.binance("BlastApi")) {
            //passthrough with no errors
        }
    }

})
