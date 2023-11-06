package io.gladiators.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int24;
import org.web3j.abi.datatypes.generated.Uint24;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
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
public class PoolFactory extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_COLLECTPROTOCOL = "collectProtocol";

    public static final String FUNC_CREATEPOOL = "createPool";

    public static final String FUNC_ENABLEFEEAMOUNT = "enableFeeAmount";

    public static final String FUNC_FEEAMOUNTTICKSPACING = "feeAmountTickSpacing";

    public static final String FUNC_FEEAMOUNTTICKSPACINGEXTRAINFO = "feeAmountTickSpacingExtraInfo";

    public static final String FUNC_GETPOOL = "getPool";

    public static final String FUNC_LMPOOLDEPLOYER = "lmPoolDeployer";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_POOLDEPLOYER = "poolDeployer";

    public static final String FUNC_SETFEEAMOUNTEXTRAINFO = "setFeeAmountExtraInfo";

    public static final String FUNC_SETFEEPROTOCOL = "setFeeProtocol";

    public static final String FUNC_SETLMPOOL = "setLmPool";

    public static final String FUNC_SETLMPOOLDEPLOYER = "setLmPoolDeployer";

    public static final String FUNC_SETOWNER = "setOwner";

    public static final String FUNC_SETWHITELISTADDRESS = "setWhiteListAddress";

    public static final Event FEEAMOUNTENABLED_EVENT = new Event("FeeAmountEnabled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint24>(true) {}, new TypeReference<Int24>(true) {}));
    ;

    public static final Event FEEAMOUNTEXTRAINFOUPDATED_EVENT = new Event("FeeAmountExtraInfoUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint24>(true) {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event OWNERCHANGED_EVENT = new Event("OwnerChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event POOLCREATED_EVENT = new Event("PoolCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint24>(true) {}, new TypeReference<Int24>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event SETLMPOOLDEPLOYER_EVENT = new Event("SetLmPoolDeployer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event WHITELISTADDED_EVENT = new Event("WhiteListAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected PoolFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PoolFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PoolFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PoolFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<FeeAmountEnabledEventResponse> getFeeAmountEnabledEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FEEAMOUNTENABLED_EVENT, transactionReceipt);
        ArrayList<FeeAmountEnabledEventResponse> responses = new ArrayList<FeeAmountEnabledEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FeeAmountEnabledEventResponse typedResponse = new FeeAmountEnabledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tickSpacing = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FeeAmountEnabledEventResponse> feeAmountEnabledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FeeAmountEnabledEventResponse>() {
            @Override
            public FeeAmountEnabledEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FEEAMOUNTENABLED_EVENT, log);
                FeeAmountEnabledEventResponse typedResponse = new FeeAmountEnabledEventResponse();
                typedResponse.log = log;
                typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tickSpacing = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FeeAmountEnabledEventResponse> feeAmountEnabledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FEEAMOUNTENABLED_EVENT));
        return feeAmountEnabledEventFlowable(filter);
    }

    public static List<FeeAmountExtraInfoUpdatedEventResponse> getFeeAmountExtraInfoUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FEEAMOUNTEXTRAINFOUPDATED_EVENT, transactionReceipt);
        ArrayList<FeeAmountExtraInfoUpdatedEventResponse> responses = new ArrayList<FeeAmountExtraInfoUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FeeAmountExtraInfoUpdatedEventResponse typedResponse = new FeeAmountExtraInfoUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.whitelistRequested = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.enabled = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FeeAmountExtraInfoUpdatedEventResponse> feeAmountExtraInfoUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FeeAmountExtraInfoUpdatedEventResponse>() {
            @Override
            public FeeAmountExtraInfoUpdatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FEEAMOUNTEXTRAINFOUPDATED_EVENT, log);
                FeeAmountExtraInfoUpdatedEventResponse typedResponse = new FeeAmountExtraInfoUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.whitelistRequested = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.enabled = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FeeAmountExtraInfoUpdatedEventResponse> feeAmountExtraInfoUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FEEAMOUNTEXTRAINFOUPDATED_EVENT));
        return feeAmountExtraInfoUpdatedEventFlowable(filter);
    }

    public static List<OwnerChangedEventResponse> getOwnerChangedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERCHANGED_EVENT, transactionReceipt);
        ArrayList<OwnerChangedEventResponse> responses = new ArrayList<OwnerChangedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnerChangedEventResponse typedResponse = new OwnerChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnerChangedEventResponse> ownerChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnerChangedEventResponse>() {
            @Override
            public OwnerChangedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERCHANGED_EVENT, log);
                OwnerChangedEventResponse typedResponse = new OwnerChangedEventResponse();
                typedResponse.log = log;
                typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnerChangedEventResponse> ownerChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERCHANGED_EVENT));
        return ownerChangedEventFlowable(filter);
    }

    public static List<PoolCreatedEventResponse> getPoolCreatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(POOLCREATED_EVENT, transactionReceipt);
        ArrayList<PoolCreatedEventResponse> responses = new ArrayList<PoolCreatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PoolCreatedEventResponse typedResponse = new PoolCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token0 = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.token1 = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.tickSpacing = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.pool = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PoolCreatedEventResponse> poolCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PoolCreatedEventResponse>() {
            @Override
            public PoolCreatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(POOLCREATED_EVENT, log);
                PoolCreatedEventResponse typedResponse = new PoolCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.token0 = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.token1 = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.tickSpacing = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.pool = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PoolCreatedEventResponse> poolCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(POOLCREATED_EVENT));
        return poolCreatedEventFlowable(filter);
    }

    public static List<SetLmPoolDeployerEventResponse> getSetLmPoolDeployerEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SETLMPOOLDEPLOYER_EVENT, transactionReceipt);
        ArrayList<SetLmPoolDeployerEventResponse> responses = new ArrayList<SetLmPoolDeployerEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetLmPoolDeployerEventResponse typedResponse = new SetLmPoolDeployerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.lmPoolDeployer = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SetLmPoolDeployerEventResponse> setLmPoolDeployerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SetLmPoolDeployerEventResponse>() {
            @Override
            public SetLmPoolDeployerEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SETLMPOOLDEPLOYER_EVENT, log);
                SetLmPoolDeployerEventResponse typedResponse = new SetLmPoolDeployerEventResponse();
                typedResponse.log = log;
                typedResponse.lmPoolDeployer = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SetLmPoolDeployerEventResponse> setLmPoolDeployerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETLMPOOLDEPLOYER_EVENT));
        return setLmPoolDeployerEventFlowable(filter);
    }

    public static List<WhiteListAddedEventResponse> getWhiteListAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WHITELISTADDED_EVENT, transactionReceipt);
        ArrayList<WhiteListAddedEventResponse> responses = new ArrayList<WhiteListAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WhiteListAddedEventResponse typedResponse = new WhiteListAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.verified = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WhiteListAddedEventResponse> whiteListAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WhiteListAddedEventResponse>() {
            @Override
            public WhiteListAddedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(WHITELISTADDED_EVENT, log);
                WhiteListAddedEventResponse typedResponse = new WhiteListAddedEventResponse();
                typedResponse.log = log;
                typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.verified = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WhiteListAddedEventResponse> whiteListAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WHITELISTADDED_EVENT));
        return whiteListAddedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> collectProtocol(String pool, String recipient, BigInteger amount0Requested, BigInteger amount1Requested) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COLLECTPROTOCOL, 
                Arrays.<Type>asList(new Address(160, pool),
                new Address(160, recipient),
                new org.web3j.abi.datatypes.generated.Uint128(amount0Requested), 
                new org.web3j.abi.datatypes.generated.Uint128(amount1Requested)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createPool(String tokenA, String tokenB, BigInteger fee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEPOOL, 
                Arrays.<Type>asList(new Address(160, tokenA),
                new Address(160, tokenB),
                new Uint24(fee)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> enableFeeAmount(BigInteger fee, BigInteger tickSpacing) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENABLEFEEAMOUNT, 
                Arrays.<Type>asList(new Uint24(fee),
                new Int24(tickSpacing)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> feeAmountTickSpacing(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FEEAMOUNTTICKSPACING, 
                Arrays.<Type>asList(new Uint24(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int24>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<Boolean, Boolean>> feeAmountTickSpacingExtraInfo(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FEEAMOUNTTICKSPACINGEXTRAINFO, 
                Arrays.<Type>asList(new Uint24(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple2<Boolean, Boolean>>(function,
                new Callable<Tuple2<Boolean, Boolean>>() {
                    @Override
                    public Tuple2<Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<Boolean, Boolean>(
                                (Boolean) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> getPool(String param0, String param1, BigInteger param2) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPOOL, 
                Arrays.<Type>asList(new Address(160, param0),
                new Address(160, param1),
                new Uint24(param2)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> lmPoolDeployer() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LMPOOLDEPLOYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> poolDeployer() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_POOLDEPLOYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setFeeAmountExtraInfo(BigInteger fee, Boolean whitelistRequested, Boolean enabled) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETFEEAMOUNTEXTRAINFO, 
                Arrays.<Type>asList(new Uint24(fee),
                new Bool(whitelistRequested),
                new Bool(enabled)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setFeeProtocol(String pool, BigInteger feeProtocol0, BigInteger feeProtocol1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETFEEPROTOCOL, 
                Arrays.<Type>asList(new Address(160, pool),
                new org.web3j.abi.datatypes.generated.Uint32(feeProtocol0), 
                new org.web3j.abi.datatypes.generated.Uint32(feeProtocol1)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setLmPool(String pool, String lmPool) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLMPOOL, 
                Arrays.<Type>asList(new Address(160, pool),
                new Address(160, lmPool)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setLmPoolDeployer(String _lmPoolDeployer) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLMPOOLDEPLOYER, 
                Arrays.<Type>asList(new Address(160, _lmPoolDeployer)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOwner(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new Address(160, _owner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWhiteListAddress(String user, Boolean verified) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETWHITELISTADDRESS, 
                Arrays.<Type>asList(new Address(160, user),
                new Bool(verified)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PoolFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PoolFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PoolFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PoolFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PoolFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PoolFactory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PoolFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PoolFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class FeeAmountEnabledEventResponse extends BaseEventResponse {
        public BigInteger fee;

        public BigInteger tickSpacing;
    }

    public static class FeeAmountExtraInfoUpdatedEventResponse extends BaseEventResponse {
        public BigInteger fee;

        public Boolean whitelistRequested;

        public Boolean enabled;
    }

    public static class OwnerChangedEventResponse extends BaseEventResponse {
        public String oldOwner;

        public String newOwner;
    }

    public static class PoolCreatedEventResponse extends BaseEventResponse {
        public String token0;

        public String token1;

        public BigInteger fee;

        public BigInteger tickSpacing;

        public String pool;
    }

    public static class SetLmPoolDeployerEventResponse extends BaseEventResponse {
        public String lmPoolDeployer;
    }

    public static class WhiteListAddedEventResponse extends BaseEventResponse {
        public String user;

        public Boolean verified;
    }
}
