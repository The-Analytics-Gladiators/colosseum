package io.gladiators.solidity;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.abi.datatypes.generated.Uint24;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
public class Quoterv2 extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_WETH9 = "WETH9";

    public static final String FUNC_DEPLOYER = "deployer";

    public static final String FUNC_FACTORY = "factory";

    public static final String FUNC_PANCAKEV3SWAPCALLBACK = "pancakeV3SwapCallback";

    public static final String FUNC_QUOTEEXACTINPUT = "quoteExactInput";

    public static final String FUNC_QUOTEEXACTINPUTSINGLE = "quoteExactInputSingle";

    public static final String FUNC_QUOTEEXACTOUTPUT = "quoteExactOutput";

    public static final String FUNC_QUOTEEXACTOUTPUTSINGLE = "quoteExactOutputSingle";

    @Deprecated
    protected Quoterv2(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Quoterv2(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Quoterv2(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Quoterv2(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<TransactionReceipt> quoteExactInput(byte[] path, BigInteger amountIn) {
        final Function function = new Function(
                FUNC_QUOTEEXACTINPUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(path), 
                new Uint256(amountIn)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> quoteExactInputSingle(QuoteExactInputSingleParams params) {
        final Function function = new Function(FUNC_QUOTEEXACTINPUTSINGLE, 
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

    public RemoteFunctionCall<TransactionReceipt> quoteExactOutput(byte[] path, BigInteger amountOut) {
        final Function function = new Function(
                FUNC_QUOTEEXACTOUTPUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(path), 
                new Uint256(amountOut)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> quoteExactOutputSingle(QuoteExactOutputSingleParams params) {
        final Function function = new Function(FUNC_QUOTEEXACTOUTPUTSINGLE, 
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
    public static Quoterv2 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Quoterv2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Quoterv2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Quoterv2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Quoterv2 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Quoterv2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Quoterv2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Quoterv2(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class QuoteExactInputSingleParams extends StaticStruct {
        public String tokenIn;

        public String tokenOut;

        public BigInteger amountIn;

        public BigInteger fee;

        public BigInteger sqrtPriceLimitX96;

        public QuoteExactInputSingleParams(String tokenIn, String tokenOut, BigInteger amountIn, BigInteger fee, BigInteger sqrtPriceLimitX96) {
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

        public QuoteExactInputSingleParams(Address tokenIn, Address tokenOut, Uint256 amountIn, Uint24 fee, Uint160 sqrtPriceLimitX96) {
            super(tokenIn, tokenOut, amountIn, fee, sqrtPriceLimitX96);
            this.tokenIn = tokenIn.getValue();
            this.tokenOut = tokenOut.getValue();
            this.amountIn = amountIn.getValue();
            this.fee = fee.getValue();
            this.sqrtPriceLimitX96 = sqrtPriceLimitX96.getValue();
        }
    }

    public static class QuoteExactOutputSingleParams extends StaticStruct {
        public String tokenIn;

        public String tokenOut;

        public BigInteger amount;

        public BigInteger fee;

        public BigInteger sqrtPriceLimitX96;

        public QuoteExactOutputSingleParams(String tokenIn, String tokenOut, BigInteger amount, BigInteger fee, BigInteger sqrtPriceLimitX96) {
            super(new Address(160, tokenIn),
                    new Address(160, tokenOut),
                    new Uint256(amount),
                    new Uint24(fee),
                    new Uint160(sqrtPriceLimitX96));
            this.tokenIn = tokenIn;
            this.tokenOut = tokenOut;
            this.amount = amount;
            this.fee = fee;
            this.sqrtPriceLimitX96 = sqrtPriceLimitX96;
        }

        public QuoteExactOutputSingleParams(Address tokenIn, Address tokenOut, Uint256 amount, Uint24 fee, Uint160 sqrtPriceLimitX96) {
            super(tokenIn, tokenOut, amount, fee, sqrtPriceLimitX96);
            this.tokenIn = tokenIn.getValue();
            this.tokenOut = tokenOut.getValue();
            this.amount = amount.getValue();
            this.fee = fee.getValue();
            this.sqrtPriceLimitX96 = sqrtPriceLimitX96.getValue();
        }
    }
}
