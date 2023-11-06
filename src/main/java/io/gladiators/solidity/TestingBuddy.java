package io.gladiators.solidity;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int24;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
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
public class TestingBuddy extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_COMPAREHIGHESTBITS = "compareHighestBits";

    public static final String FUNC_ESTIMATESWAPUNISWAP2 = "estimateSwapUniswap2";

    public static final String FUNC_ESTIMATESWAPUNISWAP3 = "estimateSwapUniswap3";

    public static final String FUNC_NEXTINITIALIZEDTICKWITHINONEWORD = "nextInitializedTickWithinOneWord";

    public static final String FUNC_PANCAKEV3SWAPCALLBACK = "pancakeV3SwapCallback";

    public static final String FUNC_SWAP = "swap";

    public static final String FUNC_UNISWAPV3SWAPCALLBACK = "uniswapV3SwapCallback";

    @Deprecated
    protected TestingBuddy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TestingBuddy(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TestingBuddy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TestingBuddy(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> compareHighestBits(BigInteger largeNum, BigInteger mask) {
        final Function function = new Function(FUNC_COMPAREHIGHESTBITS, 
                Arrays.<Type>asList(new Uint256(largeNum),
                new Uint256(mask)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> estimateSwapUniswap2(String poolV2, BigInteger amountIn, Boolean zeroForOne, BigInteger fee) {
        final Function function = new Function(FUNC_ESTIMATESWAPUNISWAP2, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, poolV2), 
                new Uint256(amountIn),
                new Bool(zeroForOne),
                new Uint256(fee)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> estimateSwapUniswap3(String pool, Boolean zeroForOne, BigInteger amountIn) {
        final Function function = new Function(FUNC_ESTIMATESWAPUNISWAP3, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pool), 
                new Bool(zeroForOne),
                new Uint256(amountIn)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple2<BigInteger, Boolean>> nextInitializedTickWithinOneWord(String pool, BigInteger tick, BigInteger tickSpacing, Boolean lte) {
        final Function function = new Function(FUNC_NEXTINITIALIZEDTICKWITHINONEWORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pool), 
                new Int24(tick),
                new Int24(tickSpacing),
                new Bool(lte)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int24>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, Boolean>>(function,
                new Callable<Tuple2<BigInteger, Boolean>>() {
                    @Override
                    public Tuple2<BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> pancakeV3SwapCallback(BigInteger amount0Delta, BigInteger amount1Delta, byte[] data, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PANCAKEV3SWAPCALLBACK, 
                Arrays.<Type>asList(new Int256(amount0Delta),
                new Int256(amount1Delta),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> swap(String pool, Boolean zeroForOne, BigInteger version, BigInteger amountIn) {
        final Function function = new Function(
                FUNC_SWAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pool), 
                new Bool(zeroForOne),
                new Uint256(version),
                new Uint256(amountIn)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> uniswapV3SwapCallback(BigInteger amount0Delta, BigInteger amount1Delta, byte[] data, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_UNISWAPV3SWAPCALLBACK, 
                Arrays.<Type>asList(new Int256(amount0Delta),
                new Int256(amount1Delta),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static TestingBuddy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestingBuddy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TestingBuddy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestingBuddy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TestingBuddy load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TestingBuddy(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TestingBuddy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TestingBuddy(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
