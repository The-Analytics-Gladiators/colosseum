package io.gladiators.solidity;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int128;
import org.web3j.abi.datatypes.generated.Int24;
import org.web3j.abi.datatypes.generated.Uint128;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class TickLens extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_GETPOPULATEDTICKSINWORD = "getPopulatedTicksInWord";

    @Deprecated
    protected TickLens(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TickLens(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TickLens(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TickLens(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<List> getPopulatedTicksInWord(String pool, BigInteger tickBitmapIndex) {
        final Function function = new Function(FUNC_GETPOPULATEDTICKSINWORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pool), 
                new org.web3j.abi.datatypes.generated.Int16(tickBitmapIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<PopulatedTick>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static TickLens load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TickLens(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TickLens load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TickLens(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TickLens load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TickLens(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TickLens load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TickLens(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class PopulatedTick extends StaticStruct {
        public BigInteger tick;

        public BigInteger liquidityNet;

        public BigInteger liquidityGross;

        public PopulatedTick(BigInteger tick, BigInteger liquidityNet, BigInteger liquidityGross) {
            super(new Int24(tick),
                    new Int128(liquidityNet),
                    new Uint128(liquidityGross));
            this.tick = tick;
            this.liquidityNet = liquidityNet;
            this.liquidityGross = liquidityGross;
        }

        public PopulatedTick(Int24 tick, Int128 liquidityNet, Uint128 liquidityGross) {
            super(tick, liquidityNet, liquidityGross);
            this.tick = tick.getValue();
            this.liquidityNet = liquidityNet.getValue();
            this.liquidityGross = liquidityGross.getValue();
        }
    }
}
