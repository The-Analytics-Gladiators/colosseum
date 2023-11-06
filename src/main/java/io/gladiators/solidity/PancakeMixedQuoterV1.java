package io.gladiators.solidity;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.abi.datatypes.generated.Uint24;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tuples.generated.Tuple4;
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
public class PancakeMixedQuoterV1 extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_WETH9 = "WETH9";

    public static final String FUNC_DEPLOYER = "deployer";

    public static final String FUNC_FACTORY = "factory";

    public static final String FUNC_FACTORYSTABLE = "factoryStable";

    public static final String FUNC_FACTORYV2 = "factoryV2";

    public static final String FUNC_PANCAKEV3SWAPCALLBACK = "pancakeV3SwapCallback";

    public static final String FUNC_QUOTEEXACTINPUT = "quoteExactInput";

    public static final String FUNC_QUOTEEXACTINPUTSINGLESTABLE = "quoteExactInputSingleStable";

    public static final String FUNC_QUOTEEXACTINPUTSINGLEV2 = "quoteExactInputSingleV2";

    public static final String FUNC_QUOTEEXACTINPUTSINGLEV3 = "quoteExactInputSingleV3";

    @Deprecated
    protected PancakeMixedQuoterV1(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PancakeMixedQuoterV1(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PancakeMixedQuoterV1(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PancakeMixedQuoterV1(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> WETH9() {
        final Function function = new Function(FUNC_WETH9, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> deployer() {
        final Function function = new Function(FUNC_DEPLOYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> factory() {
        final Function function = new Function(FUNC_FACTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> factoryStable() {
        final Function function = new Function(FUNC_FACTORYSTABLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> factoryV2() {
        final Function function = new Function(FUNC_FACTORYV2, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, BigInteger>> quoteExactInput(byte[] path, List<BigInteger> flag, BigInteger amountIn) {
        final Function function = new Function(FUNC_QUOTEEXACTINPUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(path), 
                new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(flag, Uint256.class)),
                new Uint256(amountIn)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint160>>() {}, new TypeReference<DynamicArray<Uint32>>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                convertToNative((List<Uint160>) results.get(1).getValue()), 
                                convertToNative((List<Uint32>) results.get(2).getValue()), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> quoteExactInputSingleStable(QuoteExactInputSingleStableParams params) {
        final Function function = new Function(FUNC_QUOTEEXACTINPUTSINGLESTABLE, 
                Arrays.<Type>asList(params), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> quoteExactInputSingleV2(QuoteExactInputSingleV2Params params) {
        final Function function = new Function(FUNC_QUOTEEXACTINPUTSINGLEV2, 
                Arrays.<Type>asList(params), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> quoteExactInputSingleV3(QuoteExactInputSingleV3Params params) {
        final Function function = new Function(FUNC_QUOTEEXACTINPUTSINGLEV3, 
                Arrays.<Type>asList(params), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint160>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    @Deprecated
    public static PancakeMixedQuoterV1 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakeMixedQuoterV1(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PancakeMixedQuoterV1 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakeMixedQuoterV1(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PancakeMixedQuoterV1 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PancakeMixedQuoterV1(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PancakeMixedQuoterV1 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PancakeMixedQuoterV1(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class QuoteExactInputSingleStableParams extends StaticStruct {
        public String tokenIn;

        public String tokenOut;

        public BigInteger amountIn;

        public BigInteger flag;

        public QuoteExactInputSingleStableParams(String tokenIn, String tokenOut, BigInteger amountIn, BigInteger flag) {
            super(new Address(160, tokenIn),
                    new Address(160, tokenOut),
                    new Uint256(amountIn),
                    new Uint256(flag));
            this.tokenIn = tokenIn;
            this.tokenOut = tokenOut;
            this.amountIn = amountIn;
            this.flag = flag;
        }

        public QuoteExactInputSingleStableParams(Address tokenIn, Address tokenOut, Uint256 amountIn, Uint256 flag) {
            super(tokenIn, tokenOut, amountIn, flag);
            this.tokenIn = tokenIn.getValue();
            this.tokenOut = tokenOut.getValue();
            this.amountIn = amountIn.getValue();
            this.flag = flag.getValue();
        }
    }

    public static class QuoteExactInputSingleV2Params extends StaticStruct {
        public String tokenIn;

        public String tokenOut;

        public BigInteger amountIn;

        public QuoteExactInputSingleV2Params(String tokenIn, String tokenOut, BigInteger amountIn) {
            super(new Address(160, tokenIn),
                    new Address(160, tokenOut),
                    new Uint256(amountIn));
            this.tokenIn = tokenIn;
            this.tokenOut = tokenOut;
            this.amountIn = amountIn;
        }

        public QuoteExactInputSingleV2Params(Address tokenIn, Address tokenOut, Uint256 amountIn) {
            super(tokenIn, tokenOut, amountIn);
            this.tokenIn = tokenIn.getValue();
            this.tokenOut = tokenOut.getValue();
            this.amountIn = amountIn.getValue();
        }
    }

    public static class QuoteExactInputSingleV3Params extends StaticStruct {
        public String tokenIn;

        public String tokenOut;

        public BigInteger amountIn;

        public BigInteger fee;

        public BigInteger sqrtPriceLimitX96;

        public QuoteExactInputSingleV3Params(String tokenIn, String tokenOut, BigInteger amountIn, BigInteger fee, BigInteger sqrtPriceLimitX96) {
            super(new Address(160, tokenIn),
                    new Address(160, tokenOut),
                    new Uint256(amountIn),
                    new Uint24(fee),
                    new Uint160(sqrtPriceLimitX96));
            this.tokenIn = tokenIn;
            this.tokenOut = tokenOut;
            this.amountIn = amountIn;
            this.fee = fee;
            this.sqrtPriceLimitX96 = sqrtPriceLimitX96;
        }

        public QuoteExactInputSingleV3Params(Address tokenIn, Address tokenOut, Uint256 amountIn, Uint24 fee, Uint160 sqrtPriceLimitX96) {
            super(tokenIn, tokenOut, amountIn, fee, sqrtPriceLimitX96);
            this.tokenIn = tokenIn.getValue();
            this.tokenOut = tokenOut.getValue();
            this.amountIn = amountIn.getValue();
            this.fee = fee.getValue();
            this.sqrtPriceLimitX96 = sqrtPriceLimitX96.getValue();
        }
    }
}
