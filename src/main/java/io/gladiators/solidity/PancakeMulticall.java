package io.gladiators.solidity;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
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
public class PancakeMulticall extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_GETCURRENTBLOCKTIMESTAMP = "getCurrentBlockTimestamp";

    public static final String FUNC_GETETHBALANCE = "getEthBalance";

    public static final String FUNC_MULTICALL = "multicall";

    @Deprecated
    protected PancakeMulticall(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PancakeMulticall(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PancakeMulticall(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PancakeMulticall(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getCurrentBlockTimestamp() {
        final Function function = new Function(FUNC_GETCURRENTBLOCKTIMESTAMP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getEthBalance(String addr) {
        final Function function = new Function(FUNC_GETETHBALANCE, 
                Arrays.<Type>asList(new Address(160, addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> multicall(List<Call> calls) {
        final Function function = new Function(
                FUNC_MULTICALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Call>(Call.class, calls)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PancakeMulticall load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakeMulticall(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PancakeMulticall load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakeMulticall(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PancakeMulticall load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PancakeMulticall(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PancakeMulticall load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PancakeMulticall(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class Call extends DynamicStruct {
        public String target;

        public BigInteger gasLimit;

        public byte[] callData;

        public Call(String target, BigInteger gasLimit, byte[] callData) {
            super(new Address(160, target),
                    new Uint256(gasLimit),
                    new DynamicBytes(callData));
            this.target = target;
            this.gasLimit = gasLimit;
            this.callData = callData;
        }

        public Call(Address target, Uint256 gasLimit, DynamicBytes callData) {
            super(target, gasLimit, callData);
            this.target = target.getValue();
            this.gasLimit = gasLimit.getValue();
            this.callData = callData.getValue();
        }
    }

    public static class Result extends DynamicStruct {
        public Boolean success;

        public BigInteger gasUsed;

        public byte[] returnData;

        public Result(Boolean success, BigInteger gasUsed, byte[] returnData) {
            super(new Bool(success),
                    new Uint256(gasUsed),
                    new DynamicBytes(returnData));
            this.success = success;
            this.gasUsed = gasUsed;
            this.returnData = returnData;
        }

        public Result(Bool success, Uint256 gasUsed, DynamicBytes returnData) {
            super(success, gasUsed, returnData);
            this.success = success.getValue();
            this.gasUsed = gasUsed.getValue();
            this.returnData = returnData.getValue();
        }
    }
}
