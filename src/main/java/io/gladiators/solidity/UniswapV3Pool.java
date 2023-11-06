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
import org.web3j.tuples.generated.*;
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
public class UniswapV3Pool extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_COLLECT = "collect";

    public static final String FUNC_COLLECTPROTOCOL = "collectProtocol";

    public static final String FUNC_FACTORY = "factory";

    public static final String FUNC_FEE = "fee";

    public static final String FUNC_FEEGROWTHGLOBAL0X128 = "feeGrowthGlobal0X128";

    public static final String FUNC_FEEGROWTHGLOBAL1X128 = "feeGrowthGlobal1X128";

    public static final String FUNC_FLASH = "flash";

    public static final String FUNC_INCREASEOBSERVATIONCARDINALITYNEXT = "increaseObservationCardinalityNext";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_LIQUIDITY = "liquidity";

    public static final String FUNC_MAXLIQUIDITYPERTICK = "maxLiquidityPerTick";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_OBSERVATIONS = "observations";

    public static final String FUNC_OBSERVE = "observe";

    public static final String FUNC_POSITIONS = "positions";

    public static final String FUNC_PROTOCOLFEES = "protocolFees";

    public static final String FUNC_SETFEEPROTOCOL = "setFeeProtocol";

    public static final String FUNC_SLOT0 = "slot0";

    public static final String FUNC_SNAPSHOTCUMULATIVESINSIDE = "snapshotCumulativesInside";

    public static final String FUNC_SWAP = "swap";

    public static final String FUNC_TICKBITMAP = "tickBitmap";

    public static final String FUNC_TICKSPACING = "tickSpacing";

    public static final String FUNC_TICKS = "ticks";

    public static final String FUNC_TOKEN0 = "token0";

    public static final String FUNC_TOKEN1 = "token1";

    public static final Event BURN_EVENT = new Event("Burn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event COLLECT_EVENT = new Event("Collect", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Int24>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
    ;

    public static final Event COLLECTPROTOCOL_EVENT = new Event("CollectProtocol", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
    ;

    public static final Event FLASH_EVENT = new Event("Flash", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event INCREASEOBSERVATIONCARDINALITYNEXT_EVENT = new Event("IncreaseObservationCardinalityNext", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
    ;

    public static final Event INITIALIZE_EVENT = new Event("Initialize", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint160>() {}, new TypeReference<Int24>() {}));
    ;

    public static final Event MINT_EVENT = new Event("Mint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SETFEEPROTOCOL_EVENT = new Event("SetFeeProtocol", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event SWAP_EVENT = new Event("Swap", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Uint160>() {}, new TypeReference<Uint128>() {}, new TypeReference<Int24>() {}));
    ;

    @Deprecated
    protected UniswapV3Pool(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UniswapV3Pool(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UniswapV3Pool(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UniswapV3Pool(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BurnEventResponse> getBurnEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BURN_EVENT, transactionReceipt);
        ArrayList<BurnEventResponse> responses = new ArrayList<BurnEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BurnEventResponse typedResponse = new BurnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tickLower = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tickUpper = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BurnEventResponse> burnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BurnEventResponse>() {
            @Override
            public BurnEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BURN_EVENT, log);
                BurnEventResponse typedResponse = new BurnEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tickLower = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tickUpper = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BurnEventResponse> burnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURN_EVENT));
        return burnEventFlowable(filter);
    }

    public static List<CollectEventResponse> getCollectEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(COLLECT_EVENT, transactionReceipt);
        ArrayList<CollectEventResponse> responses = new ArrayList<CollectEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CollectEventResponse typedResponse = new CollectEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tickLower = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tickUpper = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tickLower = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tickUpper = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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

    public static List<CollectProtocolEventResponse> getCollectProtocolEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(COLLECTPROTOCOL_EVENT, transactionReceipt);
        ArrayList<CollectProtocolEventResponse> responses = new ArrayList<CollectProtocolEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CollectProtocolEventResponse typedResponse = new CollectProtocolEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.recipient = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CollectProtocolEventResponse> collectProtocolEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CollectProtocolEventResponse>() {
            @Override
            public CollectProtocolEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(COLLECTPROTOCOL_EVENT, log);
                CollectProtocolEventResponse typedResponse = new CollectProtocolEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.recipient = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CollectProtocolEventResponse> collectProtocolEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COLLECTPROTOCOL_EVENT));
        return collectProtocolEventFlowable(filter);
    }

    public static List<FlashEventResponse> getFlashEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FLASH_EVENT, transactionReceipt);
        ArrayList<FlashEventResponse> responses = new ArrayList<FlashEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FlashEventResponse typedResponse = new FlashEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.recipient = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.paid0 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.paid1 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FlashEventResponse> flashEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FlashEventResponse>() {
            @Override
            public FlashEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FLASH_EVENT, log);
                FlashEventResponse typedResponse = new FlashEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.recipient = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.paid0 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.paid1 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FlashEventResponse> flashEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FLASH_EVENT));
        return flashEventFlowable(filter);
    }

    public static List<IncreaseObservationCardinalityNextEventResponse> getIncreaseObservationCardinalityNextEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INCREASEOBSERVATIONCARDINALITYNEXT_EVENT, transactionReceipt);
        ArrayList<IncreaseObservationCardinalityNextEventResponse> responses = new ArrayList<IncreaseObservationCardinalityNextEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            IncreaseObservationCardinalityNextEventResponse typedResponse = new IncreaseObservationCardinalityNextEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.observationCardinalityNextOld = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.observationCardinalityNextNew = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IncreaseObservationCardinalityNextEventResponse> increaseObservationCardinalityNextEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IncreaseObservationCardinalityNextEventResponse>() {
            @Override
            public IncreaseObservationCardinalityNextEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(INCREASEOBSERVATIONCARDINALITYNEXT_EVENT, log);
                IncreaseObservationCardinalityNextEventResponse typedResponse = new IncreaseObservationCardinalityNextEventResponse();
                typedResponse.log = log;
                typedResponse.observationCardinalityNextOld = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.observationCardinalityNextNew = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IncreaseObservationCardinalityNextEventResponse> increaseObservationCardinalityNextEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INCREASEOBSERVATIONCARDINALITYNEXT_EVENT));
        return increaseObservationCardinalityNextEventFlowable(filter);
    }

    public static List<InitializeEventResponse> getInitializeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INITIALIZE_EVENT, transactionReceipt);
        ArrayList<InitializeEventResponse> responses = new ArrayList<InitializeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            InitializeEventResponse typedResponse = new InitializeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sqrtPriceX96 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tick = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InitializeEventResponse> initializeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, InitializeEventResponse>() {
            @Override
            public InitializeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(INITIALIZE_EVENT, log);
                InitializeEventResponse typedResponse = new InitializeEventResponse();
                typedResponse.log = log;
                typedResponse.sqrtPriceX96 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tick = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<InitializeEventResponse> initializeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INITIALIZE_EVENT));
        return initializeEventFlowable(filter);
    }

    public static List<MintEventResponse> getMintEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MINT_EVENT, transactionReceipt);
        ArrayList<MintEventResponse> responses = new ArrayList<MintEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MintEventResponse typedResponse = new MintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tickLower = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tickUpper = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MintEventResponse> mintEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MintEventResponse>() {
            @Override
            public MintEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MINT_EVENT, log);
                MintEventResponse typedResponse = new MintEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tickLower = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tickUpper = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MintEventResponse> mintEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINT_EVENT));
        return mintEventFlowable(filter);
    }

    public static List<SetFeeProtocolEventResponse> getSetFeeProtocolEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SETFEEPROTOCOL_EVENT, transactionReceipt);
        ArrayList<SetFeeProtocolEventResponse> responses = new ArrayList<SetFeeProtocolEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetFeeProtocolEventResponse typedResponse = new SetFeeProtocolEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.feeProtocol0Old = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.feeProtocol1Old = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.feeProtocol0New = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.feeProtocol1New = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SetFeeProtocolEventResponse> setFeeProtocolEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SetFeeProtocolEventResponse>() {
            @Override
            public SetFeeProtocolEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SETFEEPROTOCOL_EVENT, log);
                SetFeeProtocolEventResponse typedResponse = new SetFeeProtocolEventResponse();
                typedResponse.log = log;
                typedResponse.feeProtocol0Old = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.feeProtocol1Old = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.feeProtocol0New = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.feeProtocol1New = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SetFeeProtocolEventResponse> setFeeProtocolEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETFEEPROTOCOL_EVENT));
        return setFeeProtocolEventFlowable(filter);
    }

    public static List<SwapEventResponse> getSwapEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SWAP_EVENT, transactionReceipt);
        ArrayList<SwapEventResponse> responses = new ArrayList<SwapEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SwapEventResponse typedResponse = new SwapEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.recipient = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sqrtPriceX96 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.liquidity = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.tick = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SwapEventResponse> swapEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SwapEventResponse>() {
            @Override
            public SwapEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SWAP_EVENT, log);
                SwapEventResponse typedResponse = new SwapEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.recipient = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount0 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount1 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.sqrtPriceX96 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.liquidity = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.tick = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SwapEventResponse> swapEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SWAP_EVENT));
        return swapEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger tickLower, BigInteger tickUpper, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new Int24(tickLower),
                new Int24(tickUpper),
                new Uint128(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> collect(String recipient, BigInteger tickLower, BigInteger tickUpper, BigInteger amount0Requested, BigInteger amount1Requested) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COLLECT, 
                Arrays.<Type>asList(new Address(160, recipient),
                new Int24(tickLower),
                new Int24(tickUpper),
                new Uint128(amount0Requested),
                new Uint128(amount1Requested)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> collectProtocol(String recipient, BigInteger amount0Requested, BigInteger amount1Requested) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COLLECTPROTOCOL, 
                Arrays.<Type>asList(new Address(160, recipient),
                new Uint128(amount0Requested),
                new Uint128(amount1Requested)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> factory() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FACTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> fee() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint24>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> feeGrowthGlobal0X128() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FEEGROWTHGLOBAL0X128, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> feeGrowthGlobal1X128() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FEEGROWTHGLOBAL1X128, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> flash(String recipient, BigInteger amount0, BigInteger amount1, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FLASH, 
                Arrays.<Type>asList(new Address(160, recipient),
                new Uint256(amount0),
                new Uint256(amount1),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseObservationCardinalityNext(BigInteger observationCardinalityNext) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEOBSERVATIONCARDINALITYNEXT, 
                Arrays.<Type>asList(new Uint16(observationCardinalityNext)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(BigInteger sqrtPriceX96) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Uint160(sqrtPriceX96)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> liquidity() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LIQUIDITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> maxLiquidityPerTick() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MAXLIQUIDITYPERTICK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String recipient, BigInteger tickLower, BigInteger tickUpper, BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new Address(160, recipient),
                new Int24(tickLower),
                new Int24(tickUpper),
                new Uint128(amount),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, Boolean>> observations(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OBSERVATIONS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Int56>() {}, new TypeReference<Uint160>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>> observe(List<BigInteger> secondsAgos) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OBSERVE, 
                Arrays.<Type>asList(new DynamicArray<Uint32>(
                        Uint32.class,
                        org.web3j.abi.Utils.typeMap(secondsAgos, Uint32.class))),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Int56>>() {}, new TypeReference<DynamicArray<Uint160>>() {}));
        return new RemoteFunctionCall<Tuple2<List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple2<List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple2<List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Int56>) results.get(0).getValue()), 
                                convertToNative((List<Uint160>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> positions(byte[] param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_POSITIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
        return new RemoteFunctionCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> protocolFees() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PROTOCOLFEES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> setFeeProtocol(BigInteger feeProtocol0, BigInteger feeProtocol1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETFEEPROTOCOL, 
                Arrays.<Type>asList(new Uint8(feeProtocol0),
                new Uint8(feeProtocol1)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> slot0() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SLOT0, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint160>() {}, new TypeReference<Int24>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (Boolean) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> snapshotCumulativesInside(BigInteger tickLower, BigInteger tickUpper) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SNAPSHOTCUMULATIVESINSIDE, 
                Arrays.<Type>asList(new Int24(tickLower),
                new Int24(tickUpper)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int56>() {}, new TypeReference<Uint160>() {}, new TypeReference<Uint32>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> swap(String recipient, Boolean zeroForOne, BigInteger amountSpecified, BigInteger sqrtPriceLimitX96, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SWAP, 
                Arrays.<Type>asList(new Address(160, recipient),
                new Bool(zeroForOne),
                new Int256(amountSpecified),
                new Uint160(sqrtPriceLimitX96),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> tickBitmap(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKBITMAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int16(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> tickSpacing() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKSPACING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int24>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> ticks(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKS, 
                Arrays.<Type>asList(new Int24(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}, new TypeReference<Int128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int56>() {}, new TypeReference<Uint160>() {}, new TypeReference<Uint32>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (Boolean) results.get(7).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> token0() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKEN0, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> token1() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKEN1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static UniswapV3Pool load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UniswapV3Pool(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UniswapV3Pool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UniswapV3Pool(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UniswapV3Pool load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UniswapV3Pool(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UniswapV3Pool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UniswapV3Pool(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class BurnEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger tickLower;

        public BigInteger tickUpper;

        public BigInteger amount;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class CollectEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger tickLower;

        public BigInteger tickUpper;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class CollectProtocolEventResponse extends BaseEventResponse {
        public String sender;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class FlashEventResponse extends BaseEventResponse {
        public String sender;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;

        public BigInteger paid0;

        public BigInteger paid1;
    }

    public static class IncreaseObservationCardinalityNextEventResponse extends BaseEventResponse {
        public BigInteger observationCardinalityNextOld;

        public BigInteger observationCardinalityNextNew;
    }

    public static class InitializeEventResponse extends BaseEventResponse {
        public BigInteger sqrtPriceX96;

        public BigInteger tick;
    }

    public static class MintEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger tickLower;

        public BigInteger tickUpper;

        public String sender;

        public BigInteger amount;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class SetFeeProtocolEventResponse extends BaseEventResponse {
        public BigInteger feeProtocol0Old;

        public BigInteger feeProtocol1Old;

        public BigInteger feeProtocol0New;

        public BigInteger feeProtocol1New;
    }

    public static class SwapEventResponse extends BaseEventResponse {
        public String sender;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;

        public BigInteger sqrtPriceX96;

        public BigInteger liquidity;

        public BigInteger tick;
    }
}
