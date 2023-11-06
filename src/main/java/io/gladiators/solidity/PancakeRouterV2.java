package io.gladiators.solidity;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class PancakeRouterV2 extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_WETH = "WETH";

    public static final String FUNC_ADDLIQUIDITY = "addLiquidity";

    public static final String FUNC_ADDLIQUIDITYETH = "addLiquidityETH";

    public static final String FUNC_FACTORY = "factory";

    public static final String FUNC_GETAMOUNTIN = "getAmountIn";

    public static final String FUNC_GETAMOUNTOUT = "getAmountOut";

    public static final String FUNC_GETAMOUNTSIN = "getAmountsIn";

    public static final String FUNC_GETAMOUNTSOUT = "getAmountsOut";

    public static final String FUNC_QUOTE = "quote";

    public static final String FUNC_REMOVELIQUIDITY = "removeLiquidity";

    public static final String FUNC_REMOVELIQUIDITYETH = "removeLiquidityETH";

    public static final String FUNC_REMOVELIQUIDITYETHSUPPORTINGFEEONTRANSFERTOKENS = "removeLiquidityETHSupportingFeeOnTransferTokens";

    public static final String FUNC_REMOVELIQUIDITYETHWITHPERMIT = "removeLiquidityETHWithPermit";

    public static final String FUNC_REMOVELIQUIDITYETHWITHPERMITSUPPORTINGFEEONTRANSFERTOKENS = "removeLiquidityETHWithPermitSupportingFeeOnTransferTokens";

    public static final String FUNC_REMOVELIQUIDITYWITHPERMIT = "removeLiquidityWithPermit";

    public static final String FUNC_SWAPETHFOREXACTTOKENS = "swapETHForExactTokens";

    public static final String FUNC_SWAPEXACTETHFORTOKENS = "swapExactETHForTokens";

    public static final String FUNC_SWAPEXACTETHFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS = "swapExactETHForTokensSupportingFeeOnTransferTokens";

    public static final String FUNC_SWAPEXACTTOKENSFORETH = "swapExactTokensForETH";

    public static final String FUNC_SWAPEXACTTOKENSFORETHSUPPORTINGFEEONTRANSFERTOKENS = "swapExactTokensForETHSupportingFeeOnTransferTokens";

    public static final String FUNC_SWAPEXACTTOKENSFORTOKENS = "swapExactTokensForTokens";

    public static final String FUNC_SWAPEXACTTOKENSFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS = "swapExactTokensForTokensSupportingFeeOnTransferTokens";

    public static final String FUNC_SWAPTOKENSFOREXACTETH = "swapTokensForExactETH";

    public static final String FUNC_SWAPTOKENSFOREXACTTOKENS = "swapTokensForExactTokens";

    @Deprecated
    protected PancakeRouterV2(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PancakeRouterV2(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PancakeRouterV2(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PancakeRouterV2(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> WETH() {
        final Function function = new Function(FUNC_WETH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addLiquidity(String tokenA, String tokenB, BigInteger amountADesired, BigInteger amountBDesired, BigInteger amountAMin, BigInteger amountBMin, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_ADDLIQUIDITY, 
                Arrays.<Type>asList(new Address(160, tokenA),
                new Address(160, tokenB),
                new Uint256(amountADesired),
                new Uint256(amountBDesired),
                new Uint256(amountAMin),
                new Uint256(amountBMin),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addLiquidityETH(String token, BigInteger amountTokenDesired, BigInteger amountTokenMin, BigInteger amountETHMin, String to, BigInteger deadline, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ADDLIQUIDITYETH, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(amountTokenDesired),
                new Uint256(amountTokenMin),
                new Uint256(amountETHMin),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> factory() {
        final Function function = new Function(FUNC_FACTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getAmountIn(BigInteger amountOut, BigInteger reserveIn, BigInteger reserveOut) {
        final Function function = new Function(FUNC_GETAMOUNTIN, 
                Arrays.<Type>asList(new Uint256(amountOut),
                new Uint256(reserveIn),
                new Uint256(reserveOut)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAmountOut(BigInteger amountIn, BigInteger reserveIn, BigInteger reserveOut) {
        final Function function = new Function(FUNC_GETAMOUNTOUT, 
                Arrays.<Type>asList(new Uint256(amountIn),
                new Uint256(reserveIn),
                new Uint256(reserveOut)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getAmountsIn(BigInteger amountOut, List<String> path) {
        final Function function = new Function(FUNC_GETAMOUNTSIN, 
                Arrays.<Type>asList(new Uint256(amountOut),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class))),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
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

    public RemoteFunctionCall<List> getAmountsOut(BigInteger amountIn, List<String> path) {
        final Function function = new Function(FUNC_GETAMOUNTSOUT, 
                Arrays.<Type>asList(new Uint256(amountIn),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class))),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
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

    public RemoteFunctionCall<BigInteger> quote(BigInteger amountA, BigInteger reserveA, BigInteger reserveB) {
        final Function function = new Function(FUNC_QUOTE, 
                Arrays.<Type>asList(new Uint256(amountA),
                new Uint256(reserveA),
                new Uint256(reserveB)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeLiquidity(String tokenA, String tokenB, BigInteger liquidity, BigInteger amountAMin, BigInteger amountBMin, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_REMOVELIQUIDITY, 
                Arrays.<Type>asList(new Address(160, tokenA),
                new Address(160, tokenB),
                new Uint256(liquidity),
                new Uint256(amountAMin),
                new Uint256(amountBMin),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeLiquidityETH(String token, BigInteger liquidity, BigInteger amountTokenMin, BigInteger amountETHMin, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_REMOVELIQUIDITYETH, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(liquidity),
                new Uint256(amountTokenMin),
                new Uint256(amountETHMin),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeLiquidityETHSupportingFeeOnTransferTokens(String token, BigInteger liquidity, BigInteger amountTokenMin, BigInteger amountETHMin, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_REMOVELIQUIDITYETHSUPPORTINGFEEONTRANSFERTOKENS, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(liquidity),
                new Uint256(amountTokenMin),
                new Uint256(amountETHMin),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeLiquidityETHWithPermit(String token, BigInteger liquidity, BigInteger amountTokenMin, BigInteger amountETHMin, String to, BigInteger deadline, Boolean approveMax, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_REMOVELIQUIDITYETHWITHPERMIT, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(liquidity),
                new Uint256(amountTokenMin),
                new Uint256(amountETHMin),
                new Address(160, to),
                new Uint256(deadline),
                new org.web3j.abi.datatypes.Bool(approveMax), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeLiquidityETHWithPermitSupportingFeeOnTransferTokens(String token, BigInteger liquidity, BigInteger amountTokenMin, BigInteger amountETHMin, String to, BigInteger deadline, Boolean approveMax, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_REMOVELIQUIDITYETHWITHPERMITSUPPORTINGFEEONTRANSFERTOKENS, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(liquidity),
                new Uint256(amountTokenMin),
                new Uint256(amountETHMin),
                new Address(160, to),
                new Uint256(deadline),
                new org.web3j.abi.datatypes.Bool(approveMax), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeLiquidityWithPermit(String tokenA, String tokenB, BigInteger liquidity, BigInteger amountAMin, BigInteger amountBMin, String to, BigInteger deadline, Boolean approveMax, BigInteger v, byte[] r, byte[] s) {
        final Function function = new Function(
                FUNC_REMOVELIQUIDITYWITHPERMIT, 
                Arrays.<Type>asList(new Address(160, tokenA),
                new Address(160, tokenB),
                new Uint256(liquidity),
                new Uint256(amountAMin),
                new Uint256(amountBMin),
                new Address(160, to),
                new Uint256(deadline),
                new org.web3j.abi.datatypes.Bool(approveMax), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swapETHForExactTokens(BigInteger amountOut, List<String> path, String to, BigInteger deadline, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SWAPETHFOREXACTTOKENS, 
                Arrays.<Type>asList(new Uint256(amountOut),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactETHForTokens(BigInteger amountOutMin, List<String> path, String to, BigInteger deadline, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SWAPEXACTETHFORTOKENS, 
                Arrays.<Type>asList(new Uint256(amountOutMin),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactETHForTokensSupportingFeeOnTransferTokens(BigInteger amountOutMin, List<String> path, String to, BigInteger deadline, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SWAPEXACTETHFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS, 
                Arrays.<Type>asList(new Uint256(amountOutMin),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactTokensForETH(BigInteger amountIn, BigInteger amountOutMin, List<String> path, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_SWAPEXACTTOKENSFORETH, 
                Arrays.<Type>asList(new Uint256(amountIn),
                new Uint256(amountOutMin),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactTokensForETHSupportingFeeOnTransferTokens(BigInteger amountIn, BigInteger amountOutMin, List<String> path, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_SWAPEXACTTOKENSFORETHSUPPORTINGFEEONTRANSFERTOKENS, 
                Arrays.<Type>asList(new Uint256(amountIn),
                new Uint256(amountOutMin),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactTokensForTokens(BigInteger amountIn, BigInteger amountOutMin, List<String> path, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_SWAPEXACTTOKENSFORTOKENS, 
                Arrays.<Type>asList(new Uint256(amountIn),
                new Uint256(amountOutMin),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swapExactTokensForTokensSupportingFeeOnTransferTokens(BigInteger amountIn, BigInteger amountOutMin, List<String> path, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_SWAPEXACTTOKENSFORTOKENSSUPPORTINGFEEONTRANSFERTOKENS, 
                Arrays.<Type>asList(new Uint256(amountIn),
                new Uint256(amountOutMin),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swapTokensForExactETH(BigInteger amountOut, BigInteger amountInMax, List<String> path, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_SWAPTOKENSFOREXACTETH, 
                Arrays.<Type>asList(new Uint256(amountOut),
                new Uint256(amountInMax),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swapTokensForExactTokens(BigInteger amountOut, BigInteger amountInMax, List<String> path, String to, BigInteger deadline) {
        final Function function = new Function(
                FUNC_SWAPTOKENSFOREXACTTOKENS, 
                Arrays.<Type>asList(new Uint256(amountOut),
                new Uint256(amountInMax),
                new DynamicArray<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(path, Address.class)),
                new Address(160, to),
                new Uint256(deadline)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PancakeRouterV2 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakeRouterV2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PancakeRouterV2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakeRouterV2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PancakeRouterV2 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PancakeRouterV2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PancakeRouterV2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PancakeRouterV2(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
