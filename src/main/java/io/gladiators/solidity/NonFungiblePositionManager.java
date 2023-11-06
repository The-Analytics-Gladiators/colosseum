package io.gladiators.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple12;
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
public class NonFungiblePositionManager extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_DOMAIN_SEPARATOR = "DOMAIN_SEPARATOR";

    public static final String FUNC_PERMIT_TYPEHASH = "PERMIT_TYPEHASH";

    public static final String FUNC_WETH9 = "WETH9";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BASEURI = "baseURI";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_COLLECT = "collect";

    public static final String FUNC_CREATEANDINITIALIZEPOOLIFNECESSARY = "createAndInitializePoolIfNecessary";

    public static final String FUNC_DECREASELIQUIDITY = "decreaseLiquidity";

    public static final String FUNC_DEPLOYER = "deployer";

    public static final String FUNC_FACTORY = "factory";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_INCREASELIQUIDITY = "increaseLiquidity";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_MULTICALL = "multicall";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_PANCAKEV3MINTCALLBACK = "pancakeV3MintCallback";

    public static final String FUNC_PERMIT = "permit";

    public static final String FUNC_POSITIONS = "positions";

    public static final String FUNC_REFUNDETH = "refundETH";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SELFPERMIT = "selfPermit";

    public static final String FUNC_SELFPERMITALLOWED = "selfPermitAllowed";

    public static final String FUNC_SELFPERMITALLOWEDIFNECESSARY = "selfPermitAllowedIfNecessary";

    public static final String FUNC_SELFPERMITIFNECESSARY = "selfPermitIfNecessary";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SWEEPTOKEN = "sweepToken";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENBYINDEX = "tokenByIndex";

    public static final String FUNC_TOKENOFOWNERBYINDEX = "tokenOfOwnerByIndex";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_UNWRAPWETH9 = "unwrapWETH9";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event COLLECT_EVENT = new Event("Collect", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event DECREASELIQUIDITY_EVENT = new Event("DecreaseLiquidity", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event INCREASELIQUIDITY_EVENT = new Event("IncreaseLiquidity", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected NonFungiblePositionManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NonFungiblePositionManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NonFungiblePositionManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NonFungiblePositionManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public static List<CollectEventResponse> getCollectEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(COLLECT_EVENT, transactionReceipt);
        ArrayList<CollectEventResponse> responses = new ArrayList<CollectEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CollectEventResponse typedResponse = new CollectEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.recipient = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CollectEventResponse> collectEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CollectEventResponse>() {
            @Override
            public CollectEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(COLLECT_EVENT, log);
                CollectEventResponse typedResponse = new CollectEventResponse();
                typedResponse.log = log;
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.recipient = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CollectEventResponse> collectEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COLLECT_EVENT));
        return collectEventFlowable(filter);
    }

    public static List<DecreaseLiquidityEventResponse> getDecreaseLiquidityEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DECREASELIQUIDITY_EVENT, transactionReceipt);
        ArrayList<DecreaseLiquidityEventResponse> responses = new ArrayList<DecreaseLiquidityEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DecreaseLiquidityEventResponse typedResponse = new DecreaseLiquidityEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.liquidity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DecreaseLiquidityEventResponse> decreaseLiquidityEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DecreaseLiquidityEventResponse>() {
            @Override
            public DecreaseLiquidityEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(DECREASELIQUIDITY_EVENT, log);
                DecreaseLiquidityEventResponse typedResponse = new DecreaseLiquidityEventResponse();
                typedResponse.log = log;
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.liquidity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DecreaseLiquidityEventResponse> decreaseLiquidityEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DECREASELIQUIDITY_EVENT));
        return decreaseLiquidityEventFlowable(filter);
    }

    public static List<IncreaseLiquidityEventResponse> getIncreaseLiquidityEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INCREASELIQUIDITY_EVENT, transactionReceipt);
        ArrayList<IncreaseLiquidityEventResponse> responses = new ArrayList<IncreaseLiquidityEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            IncreaseLiquidityEventResponse typedResponse = new IncreaseLiquidityEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.liquidity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IncreaseLiquidityEventResponse> increaseLiquidityEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IncreaseLiquidityEventResponse>() {
            @Override
            public IncreaseLiquidityEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(INCREASELIQUIDITY_EVENT, log);
                IncreaseLiquidityEventResponse typedResponse = new IncreaseLiquidityEventResponse();
                typedResponse.log = log;
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.liquidity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IncreaseLiquidityEventResponse> increaseLiquidityEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INCREASELIQUIDITY_EVENT));
        return increaseLiquidityEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> DOMAIN_SEPARATOR() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DOMAIN_SEPARATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> PERMIT_TYPEHASH() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PERMIT_TYPEHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> WETH9() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WETH9, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(160, owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> baseURI() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BASEURI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger tokenId, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> collect(CollectParams params, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COLLECT, 
                Arrays.<Type>asList(params), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> createAndInitializePoolIfNecessary(String token0, String token1, BigInteger fee, BigInteger sqrtPriceX96, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEANDINITIALIZEPOOLIFNECESSARY, 
                Arrays.<Type>asList(new Address(160, token0),
                new Address(160, token1),
                new Uint24(fee),
                new org.web3j.abi.datatypes.generated.Uint160(sqrtPriceX96)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseLiquidity(DecreaseLiquidityParams params, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASELIQUIDITY, 
                Arrays.<Type>asList(params), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> deployer() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEPLOYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> factory() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FACTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseLiquidity(IncreaseLiquidityParams params, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASELIQUIDITY, 
                Arrays.<Type>asList(params), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new Address(160, owner),
                new Address(160, operator)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(MintParams params, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(params), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> multicall(List<byte[]> data, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MULTICALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(data, org.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pancakeV3MintCallback(BigInteger amount0Owed, BigInteger amount1Owed, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PANCAKEV3MINTCALLBACK, 
                Arrays.<Type>asList(new Uint256(amount0Owed),
                new Uint256(amount1Owed),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> permit(String spender, BigInteger tokenId, BigInteger deadline, BigInteger v, byte[] r, byte[] s, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PERMIT, 
                Arrays.<Type>asList(new Address(160, spender),
                new Uint256(tokenId),
                new Uint256(deadline),
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new Bytes32(r),
                new Bytes32(s)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Tuple12<BigInteger, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> positions(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_POSITIONS, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint96>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint24>() {}, new TypeReference<Int24>() {}, new TypeReference<Int24>() {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
        return new RemoteFunctionCall<Tuple12<BigInteger, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple12<BigInteger, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple12<BigInteger, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple12<BigInteger, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue(), 
                                (BigInteger) results.get(9).getValue(), 
                                (BigInteger) results.get(10).getValue(), 
                                (BigInteger) results.get(11).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> refundETH(BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REFUNDETH, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId),
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> selfPermit(String token, BigInteger value, BigInteger deadline, BigInteger v, byte[] r, byte[] s, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELFPERMIT, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(value),
                new Uint256(deadline),
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new Bytes32(r),
                new Bytes32(s)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> selfPermitAllowed(String token, BigInteger nonce, BigInteger expiry, BigInteger v, byte[] r, byte[] s, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELFPERMITALLOWED, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(nonce),
                new Uint256(expiry),
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new Bytes32(r),
                new Bytes32(s)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> selfPermitAllowedIfNecessary(String token, BigInteger nonce, BigInteger expiry, BigInteger v, byte[] r, byte[] s, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELFPERMITALLOWEDIFNECESSARY, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(nonce),
                new Uint256(expiry),
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new Bytes32(r),
                new Bytes32(s)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> selfPermitIfNecessary(String token, BigInteger value, BigInteger deadline, BigInteger v, byte[] r, byte[] s, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELFPERMITIFNECESSARY, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(value),
                new Uint256(deadline),
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new Bytes32(r),
                new Bytes32(s)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new Address(160, operator),
                new Bool(approved)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sweepToken(String token, BigInteger amountMinimum, String recipient, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SWEEPTOKEN, 
                Arrays.<Type>asList(new Address(160, token),
                new Uint256(amountMinimum),
                new Address(160, recipient)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> tokenByIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENBYINDEX, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> tokenOfOwnerByIndex(String owner, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENOFOWNERBYINDEX, 
                Arrays.<Type>asList(new Address(160, owner),
                new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unwrapWETH9(BigInteger amountMinimum, String recipient, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNWRAPWETH9, 
                Arrays.<Type>asList(new Uint256(amountMinimum),
                new Address(160, recipient)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static NonFungiblePositionManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NonFungiblePositionManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NonFungiblePositionManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NonFungiblePositionManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NonFungiblePositionManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NonFungiblePositionManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NonFungiblePositionManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NonFungiblePositionManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class CollectParams extends StaticStruct {
        public BigInteger tokenId;

        public String recipient;

        public BigInteger amount0Max;

        public BigInteger amount1Max;

        public CollectParams(BigInteger tokenId, String recipient, BigInteger amount0Max, BigInteger amount1Max) {
            super(new Uint256(tokenId),
                    new Address(160, recipient),
                    new Uint128(amount0Max),
                    new Uint128(amount1Max));
            this.tokenId = tokenId;
            this.recipient = recipient;
            this.amount0Max = amount0Max;
            this.amount1Max = amount1Max;
        }

        public CollectParams(Uint256 tokenId, Address recipient, Uint128 amount0Max, Uint128 amount1Max) {
            super(tokenId, recipient, amount0Max, amount1Max);
            this.tokenId = tokenId.getValue();
            this.recipient = recipient.getValue();
            this.amount0Max = amount0Max.getValue();
            this.amount1Max = amount1Max.getValue();
        }
    }

    public static class DecreaseLiquidityParams extends StaticStruct {
        public BigInteger tokenId;

        public BigInteger liquidity;

        public BigInteger amount0Min;

        public BigInteger amount1Min;

        public BigInteger deadline;

        public DecreaseLiquidityParams(BigInteger tokenId, BigInteger liquidity, BigInteger amount0Min, BigInteger amount1Min, BigInteger deadline) {
            super(new Uint256(tokenId),
                    new Uint128(liquidity),
                    new Uint256(amount0Min),
                    new Uint256(amount1Min),
                    new Uint256(deadline));
            this.tokenId = tokenId;
            this.liquidity = liquidity;
            this.amount0Min = amount0Min;
            this.amount1Min = amount1Min;
            this.deadline = deadline;
        }

        public DecreaseLiquidityParams(Uint256 tokenId, Uint128 liquidity, Uint256 amount0Min, Uint256 amount1Min, Uint256 deadline) {
            super(tokenId, liquidity, amount0Min, amount1Min, deadline);
            this.tokenId = tokenId.getValue();
            this.liquidity = liquidity.getValue();
            this.amount0Min = amount0Min.getValue();
            this.amount1Min = amount1Min.getValue();
            this.deadline = deadline.getValue();
        }
    }

    public static class IncreaseLiquidityParams extends StaticStruct {
        public BigInteger tokenId;

        public BigInteger amount0Desired;

        public BigInteger amount1Desired;

        public BigInteger amount0Min;

        public BigInteger amount1Min;

        public BigInteger deadline;

        public IncreaseLiquidityParams(BigInteger tokenId, BigInteger amount0Desired, BigInteger amount1Desired, BigInteger amount0Min, BigInteger amount1Min, BigInteger deadline) {
            super(new Uint256(tokenId),
                    new Uint256(amount0Desired),
                    new Uint256(amount1Desired),
                    new Uint256(amount0Min),
                    new Uint256(amount1Min),
                    new Uint256(deadline));
            this.tokenId = tokenId;
            this.amount0Desired = amount0Desired;
            this.amount1Desired = amount1Desired;
            this.amount0Min = amount0Min;
            this.amount1Min = amount1Min;
            this.deadline = deadline;
        }

        public IncreaseLiquidityParams(Uint256 tokenId, Uint256 amount0Desired, Uint256 amount1Desired, Uint256 amount0Min, Uint256 amount1Min, Uint256 deadline) {
            super(tokenId, amount0Desired, amount1Desired, amount0Min, amount1Min, deadline);
            this.tokenId = tokenId.getValue();
            this.amount0Desired = amount0Desired.getValue();
            this.amount1Desired = amount1Desired.getValue();
            this.amount0Min = amount0Min.getValue();
            this.amount1Min = amount1Min.getValue();
            this.deadline = deadline.getValue();
        }
    }

    public static class MintParams extends StaticStruct {
        public String token0;

        public String token1;

        public BigInteger fee;

        public BigInteger tickLower;

        public BigInteger tickUpper;

        public BigInteger amount0Desired;

        public BigInteger amount1Desired;

        public BigInteger amount0Min;

        public BigInteger amount1Min;

        public String recipient;

        public BigInteger deadline;

        public MintParams(String token0, String token1, BigInteger fee, BigInteger tickLower, BigInteger tickUpper, BigInteger amount0Desired, BigInteger amount1Desired, BigInteger amount0Min, BigInteger amount1Min, String recipient, BigInteger deadline) {
            super(new Address(160, token0),
                    new Address(160, token1),
                    new Uint24(fee),
                    new Int24(tickLower),
                    new Int24(tickUpper),
                    new Uint256(amount0Desired),
                    new Uint256(amount1Desired),
                    new Uint256(amount0Min),
                    new Uint256(amount1Min),
                    new Address(160, recipient),
                    new Uint256(deadline));
            this.token0 = token0;
            this.token1 = token1;
            this.fee = fee;
            this.tickLower = tickLower;
            this.tickUpper = tickUpper;
            this.amount0Desired = amount0Desired;
            this.amount1Desired = amount1Desired;
            this.amount0Min = amount0Min;
            this.amount1Min = amount1Min;
            this.recipient = recipient;
            this.deadline = deadline;
        }

        public MintParams(Address token0, Address token1, Uint24 fee, Int24 tickLower, Int24 tickUpper, Uint256 amount0Desired, Uint256 amount1Desired, Uint256 amount0Min, Uint256 amount1Min, Address recipient, Uint256 deadline) {
            super(token0, token1, fee, tickLower, tickUpper, amount0Desired, amount1Desired, amount0Min, amount1Min, recipient, deadline);
            this.token0 = token0.getValue();
            this.token1 = token1.getValue();
            this.fee = fee.getValue();
            this.tickLower = tickLower.getValue();
            this.tickUpper = tickUpper.getValue();
            this.amount0Desired = amount0Desired.getValue();
            this.amount1Desired = amount1Desired.getValue();
            this.amount0Min = amount0Min.getValue();
            this.amount1Min = amount1Min.getValue();
            this.recipient = recipient.getValue();
            this.deadline = deadline.getValue();
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class CollectEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class DecreaseLiquidityEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public BigInteger liquidity;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class IncreaseLiquidityEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public BigInteger liquidity;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }
}
