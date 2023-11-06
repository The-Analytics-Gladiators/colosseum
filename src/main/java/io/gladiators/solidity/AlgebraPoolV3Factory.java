package io.gladiators.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple9;
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
public class AlgebraPoolV3Factory extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_BASEFEECONFIGURATION = "baseFeeConfiguration";

    public static final String FUNC_CREATEPOOL = "createPool";

    public static final String FUNC_FARMINGADDRESS = "farmingAddress";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_POOLBYPAIR = "poolByPair";

    public static final String FUNC_POOLDEPLOYER = "poolDeployer";

    public static final String FUNC_SETBASEFEECONFIGURATION = "setBaseFeeConfiguration";

    public static final String FUNC_SETFARMINGADDRESS = "setFarmingAddress";

    public static final String FUNC_SETOWNER = "setOwner";

    public static final String FUNC_SETVAULTADDRESS = "setVaultAddress";

    public static final String FUNC_VAULTADDRESS = "vaultAddress";

    public static final Event FARMINGADDRESS_EVENT = new Event("FarmingAddress", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event FEECONFIGURATION_EVENT = new Event("FeeConfiguration", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
    ;

    public static final Event OWNER_EVENT = new Event("Owner", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event POOL_EVENT = new Event("Pool", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event VAULTADDRESS_EVENT = new Event("VaultAddress", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected AlgebraPoolV3Factory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AlgebraPoolV3Factory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AlgebraPoolV3Factory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AlgebraPoolV3Factory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<FarmingAddressEventResponse> getFarmingAddressEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FARMINGADDRESS_EVENT, transactionReceipt);
        ArrayList<FarmingAddressEventResponse> responses = new ArrayList<FarmingAddressEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FarmingAddressEventResponse typedResponse = new FarmingAddressEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newFarmingAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FarmingAddressEventResponse> farmingAddressEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FarmingAddressEventResponse>() {
            @Override
            public FarmingAddressEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FARMINGADDRESS_EVENT, log);
                FarmingAddressEventResponse typedResponse = new FarmingAddressEventResponse();
                typedResponse.log = log;
                typedResponse.newFarmingAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FarmingAddressEventResponse> farmingAddressEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FARMINGADDRESS_EVENT));
        return farmingAddressEventFlowable(filter);
    }

    public static List<FeeConfigurationEventResponse> getFeeConfigurationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FEECONFIGURATION_EVENT, transactionReceipt);
        ArrayList<FeeConfigurationEventResponse> responses = new ArrayList<FeeConfigurationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FeeConfigurationEventResponse typedResponse = new FeeConfigurationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.alpha1 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.alpha2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.beta1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.beta2 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.gamma1 = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.gamma2 = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.volumeBeta = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.volumeGamma = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.baseFee = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FeeConfigurationEventResponse> feeConfigurationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FeeConfigurationEventResponse>() {
            @Override
            public FeeConfigurationEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FEECONFIGURATION_EVENT, log);
                FeeConfigurationEventResponse typedResponse = new FeeConfigurationEventResponse();
                typedResponse.log = log;
                typedResponse.alpha1 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.alpha2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.beta1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.beta2 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.gamma1 = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.gamma2 = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.volumeBeta = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse.volumeGamma = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
                typedResponse.baseFee = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FeeConfigurationEventResponse> feeConfigurationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FEECONFIGURATION_EVENT));
        return feeConfigurationEventFlowable(filter);
    }

    public static List<OwnerEventResponse> getOwnerEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNER_EVENT, transactionReceipt);
        ArrayList<OwnerEventResponse> responses = new ArrayList<OwnerEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnerEventResponse typedResponse = new OwnerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnerEventResponse> ownerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnerEventResponse>() {
            @Override
            public OwnerEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNER_EVENT, log);
                OwnerEventResponse typedResponse = new OwnerEventResponse();
                typedResponse.log = log;
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnerEventResponse> ownerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNER_EVENT));
        return ownerEventFlowable(filter);
    }

    public static List<PoolEventResponse> getPoolEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(POOL_EVENT, transactionReceipt);
        ArrayList<PoolEventResponse> responses = new ArrayList<PoolEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PoolEventResponse typedResponse = new PoolEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token0 = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.token1 = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.pool = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PoolEventResponse> poolEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PoolEventResponse>() {
            @Override
            public PoolEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(POOL_EVENT, log);
                PoolEventResponse typedResponse = new PoolEventResponse();
                typedResponse.log = log;
                typedResponse.token0 = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.token1 = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.pool = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PoolEventResponse> poolEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(POOL_EVENT));
        return poolEventFlowable(filter);
    }

    public static List<VaultAddressEventResponse> getVaultAddressEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VAULTADDRESS_EVENT, transactionReceipt);
        ArrayList<VaultAddressEventResponse> responses = new ArrayList<VaultAddressEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            VaultAddressEventResponse typedResponse = new VaultAddressEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newVaultAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VaultAddressEventResponse> vaultAddressEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VaultAddressEventResponse>() {
            @Override
            public VaultAddressEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VAULTADDRESS_EVENT, log);
                VaultAddressEventResponse typedResponse = new VaultAddressEventResponse();
                typedResponse.log = log;
                typedResponse.newVaultAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VaultAddressEventResponse> vaultAddressEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VAULTADDRESS_EVENT));
        return vaultAddressEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple9<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> baseFeeConfiguration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BASEFEECONFIGURATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
        return new RemoteFunctionCall<Tuple9<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple9<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple9<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createPool(String tokenA, String tokenB) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEPOOL, 
                Arrays.<Type>asList(new Address(160, tokenA),
                new Address(160, tokenB)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> farmingAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FARMINGADDRESS, 
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

    public RemoteFunctionCall<String> poolByPair(String param0, String param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_POOLBYPAIR, 
                Arrays.<Type>asList(new Address(160, param0),
                new Address(160, param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> poolDeployer() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_POOLDEPLOYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setBaseFeeConfiguration(BigInteger alpha1, BigInteger alpha2, BigInteger beta1, BigInteger beta2, BigInteger gamma1, BigInteger gamma2, BigInteger volumeBeta, BigInteger volumeGamma, BigInteger baseFee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBASEFEECONFIGURATION, 
                Arrays.<Type>asList(new Uint16(alpha1),
                new Uint16(alpha2),
                new Uint32(beta1),
                new Uint32(beta2),
                new Uint16(gamma1),
                new Uint16(gamma2),
                new Uint32(volumeBeta),
                new Uint16(volumeGamma),
                new Uint16(baseFee)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setFarmingAddress(String _farmingAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETFARMINGADDRESS, 
                Arrays.<Type>asList(new Address(160, _farmingAddress)),
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

    public RemoteFunctionCall<TransactionReceipt> setVaultAddress(String _vaultAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETVAULTADDRESS, 
                Arrays.<Type>asList(new Address(160, _vaultAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> vaultAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VAULTADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static AlgebraPoolV3Factory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AlgebraPoolV3Factory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AlgebraPoolV3Factory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AlgebraPoolV3Factory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AlgebraPoolV3Factory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AlgebraPoolV3Factory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AlgebraPoolV3Factory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AlgebraPoolV3Factory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class FarmingAddressEventResponse extends BaseEventResponse {
        public String newFarmingAddress;
    }

    public static class FeeConfigurationEventResponse extends BaseEventResponse {
        public BigInteger alpha1;

        public BigInteger alpha2;

        public BigInteger beta1;

        public BigInteger beta2;

        public BigInteger gamma1;

        public BigInteger gamma2;

        public BigInteger volumeBeta;

        public BigInteger volumeGamma;

        public BigInteger baseFee;
    }

    public static class OwnerEventResponse extends BaseEventResponse {
        public String newOwner;
    }

    public static class PoolEventResponse extends BaseEventResponse {
        public String token0;

        public String token1;

        public String pool;
    }

    public static class VaultAddressEventResponse extends BaseEventResponse {
        public String newVaultAddress;
    }
}
