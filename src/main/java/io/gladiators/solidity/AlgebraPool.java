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
public class AlgebraPool extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_ACTIVEINCENTIVE = "activeIncentive";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_COLLECT = "collect";

    public static final String FUNC_DATASTORAGEOPERATOR = "dataStorageOperator";

    public static final String FUNC_FACTORY = "factory";

    public static final String FUNC_FLASH = "flash";

    public static final String FUNC_GETINNERCUMULATIVES = "getInnerCumulatives";

    public static final String FUNC_GETTIMEPOINTS = "getTimepoints";

    public static final String FUNC_GLOBALSTATE = "globalState";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_LIQUIDITY = "liquidity";

    public static final String FUNC_LIQUIDITYCOOLDOWN = "liquidityCooldown";

    public static final String FUNC_MAXLIQUIDITYPERTICK = "maxLiquidityPerTick";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_POSITIONS = "positions";

    public static final String FUNC_SETCOMMUNITYFEE = "setCommunityFee";

    public static final String FUNC_SETINCENTIVE = "setIncentive";

    public static final String FUNC_SETLIQUIDITYCOOLDOWN = "setLiquidityCooldown";

    public static final String FUNC_SWAP = "swap";

    public static final String FUNC_SWAPSUPPORTINGFEEONINPUTTOKENS = "swapSupportingFeeOnInputTokens";

    public static final String FUNC_TICKSPACING = "tickSpacing";

    public static final String FUNC_TICKTABLE = "tickTable";

    public static final String FUNC_TICKS = "ticks";

    public static final String FUNC_TIMEPOINTS = "timepoints";

    public static final String FUNC_TOKEN0 = "token0";

    public static final String FUNC_TOKEN1 = "token1";

    public static final String FUNC_TOTALFEEGROWTH0TOKEN = "totalFeeGrowth0Token";

    public static final String FUNC_TOTALFEEGROWTH1TOKEN = "totalFeeGrowth1Token";

    public static final Event BURN_EVENT = new Event("Burn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event COLLECT_EVENT = new Event("Collect", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Int24>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
    ;

    public static final Event COMMUNITYFEE_EVENT = new Event("CommunityFee", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
    ;

    public static final Event FEE_EVENT = new Event("Fee", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    ;

    public static final Event FLASH_EVENT = new Event("Flash", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event INCENTIVE_EVENT = new Event("Incentive", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event INITIALIZE_EVENT = new Event("Initialize", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint160>() {}, new TypeReference<Int24>() {}));
    ;

    public static final Event LIQUIDITYCOOLDOWN_EVENT = new Event("LiquidityCooldown", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
    ;

    public static final Event MINT_EVENT = new Event("Mint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Int24>(true) {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SWAP_EVENT = new Event("Swap", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Uint160>() {}, new TypeReference<Uint128>() {}, new TypeReference<Int24>() {}));
    ;

    @Deprecated
    protected AlgebraPool(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AlgebraPool(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AlgebraPool(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AlgebraPool(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BurnEventResponse> getBurnEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BURN_EVENT, transactionReceipt);
        ArrayList<BurnEventResponse> responses = new ArrayList<BurnEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BurnEventResponse typedResponse = new BurnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.bottomTick = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.topTick = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.liquidityAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.bottomTick = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.topTick = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.liquidityAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.bottomTick = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.topTick = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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
                typedResponse.bottomTick = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.topTick = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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

    public static List<CommunityFeeEventResponse> getCommunityFeeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(COMMUNITYFEE_EVENT, transactionReceipt);
        ArrayList<CommunityFeeEventResponse> responses = new ArrayList<CommunityFeeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CommunityFeeEventResponse typedResponse = new CommunityFeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.communityFee0New = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.communityFee1New = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CommunityFeeEventResponse> communityFeeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CommunityFeeEventResponse>() {
            @Override
            public CommunityFeeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(COMMUNITYFEE_EVENT, log);
                CommunityFeeEventResponse typedResponse = new CommunityFeeEventResponse();
                typedResponse.log = log;
                typedResponse.communityFee0New = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.communityFee1New = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CommunityFeeEventResponse> communityFeeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COMMUNITYFEE_EVENT));
        return communityFeeEventFlowable(filter);
    }

    public static List<FeeEventResponse> getFeeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FEE_EVENT, transactionReceipt);
        ArrayList<FeeEventResponse> responses = new ArrayList<FeeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FeeEventResponse typedResponse = new FeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fee = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FeeEventResponse> feeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FeeEventResponse>() {
            @Override
            public FeeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(FEE_EVENT, log);
                FeeEventResponse typedResponse = new FeeEventResponse();
                typedResponse.log = log;
                typedResponse.fee = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FeeEventResponse> feeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FEE_EVENT));
        return feeEventFlowable(filter);
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

    public static List<IncentiveEventResponse> getIncentiveEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INCENTIVE_EVENT, transactionReceipt);
        ArrayList<IncentiveEventResponse> responses = new ArrayList<IncentiveEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            IncentiveEventResponse typedResponse = new IncentiveEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.virtualPoolAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IncentiveEventResponse> incentiveEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IncentiveEventResponse>() {
            @Override
            public IncentiveEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(INCENTIVE_EVENT, log);
                IncentiveEventResponse typedResponse = new IncentiveEventResponse();
                typedResponse.log = log;
                typedResponse.virtualPoolAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IncentiveEventResponse> incentiveEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INCENTIVE_EVENT));
        return incentiveEventFlowable(filter);
    }

    public static List<InitializeEventResponse> getInitializeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INITIALIZE_EVENT, transactionReceipt);
        ArrayList<InitializeEventResponse> responses = new ArrayList<InitializeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            InitializeEventResponse typedResponse = new InitializeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
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

    public static List<LiquidityCooldownEventResponse> getLiquidityCooldownEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(LIQUIDITYCOOLDOWN_EVENT, transactionReceipt);
        ArrayList<LiquidityCooldownEventResponse> responses = new ArrayList<LiquidityCooldownEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            LiquidityCooldownEventResponse typedResponse = new LiquidityCooldownEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.liquidityCooldown = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LiquidityCooldownEventResponse> liquidityCooldownEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LiquidityCooldownEventResponse>() {
            @Override
            public LiquidityCooldownEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(LIQUIDITYCOOLDOWN_EVENT, log);
                LiquidityCooldownEventResponse typedResponse = new LiquidityCooldownEventResponse();
                typedResponse.log = log;
                typedResponse.liquidityCooldown = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LiquidityCooldownEventResponse> liquidityCooldownEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LIQUIDITYCOOLDOWN_EVENT));
        return liquidityCooldownEventFlowable(filter);
    }

    public static List<MintEventResponse> getMintEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MINT_EVENT, transactionReceipt);
        ArrayList<MintEventResponse> responses = new ArrayList<MintEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MintEventResponse typedResponse = new MintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.bottomTick = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.topTick = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.liquidityAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.bottomTick = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.topTick = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.liquidityAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
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
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
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

    public RemoteFunctionCall<String> activeIncentive() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ACTIVEINCENTIVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger bottomTick, BigInteger topTick, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new Int24(bottomTick),
                new Int24(topTick),
                new Uint128(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> collect(String recipient, BigInteger bottomTick, BigInteger topTick, BigInteger amount0Requested, BigInteger amount1Requested) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_COLLECT, 
                Arrays.<Type>asList(new Address(160, recipient),
                new Int24(bottomTick),
                new Int24(topTick),
                new Uint128(amount0Requested),
                new Uint128(amount1Requested)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> dataStorageOperator() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DATASTORAGEOPERATOR, 
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

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getInnerCumulatives(BigInteger bottomTick, BigInteger topTick) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINNERCUMULATIVES, 
                Arrays.<Type>asList(new Int24(bottomTick),
                new Int24(topTick)),
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

    public RemoteFunctionCall<Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>> getTimepoints(List<BigInteger> secondsAgos) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTIMEPOINTS, 
                Arrays.<Type>asList(new DynamicArray<Uint32>(
                        Uint32.class,
                        org.web3j.abi.Utils.typeMap(secondsAgos, Uint32.class))),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Int56>>() {}, new TypeReference<DynamicArray<Uint160>>() {}, new TypeReference<DynamicArray<Uint112>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Int56>) results.get(0).getValue()), 
                                convertToNative((List<Uint160>) results.get(1).getValue()), 
                                convertToNative((List<Uint112>) results.get(2).getValue()), 
                                convertToNative((List<Uint256>) results.get(3).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> globalState() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GLOBALSTATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint160>() {}, new TypeReference<Int24>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}, new TypeReference<Bool>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> initialize(BigInteger initialPrice) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Uint160(initialPrice)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> liquidity() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LIQUIDITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> liquidityCooldown() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LIQUIDITYCOOLDOWN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> maxLiquidityPerTick() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MAXLIQUIDITYPERTICK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String sender, String recipient, BigInteger bottomTick, BigInteger topTick, BigInteger liquidityDesired, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new Address(160, sender),
                new Address(160, recipient),
                new Int24(bottomTick),
                new Int24(topTick),
                new Uint128(liquidityDesired),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> positions(byte[] param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_POSITIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint128>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint128>() {}, new TypeReference<Uint128>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> setCommunityFee(BigInteger communityFee0, BigInteger communityFee1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCOMMUNITYFEE, 
                Arrays.<Type>asList(new Uint16(communityFee0),
                new Uint16(communityFee1)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setIncentive(String virtualPoolAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETINCENTIVE, 
                Arrays.<Type>asList(new Address(160, virtualPoolAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setLiquidityCooldown(BigInteger newLiquidityCooldown) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLIQUIDITYCOOLDOWN, 
                Arrays.<Type>asList(new Uint32(newLiquidityCooldown)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swap(String recipient, Boolean zeroToOne, BigInteger amountRequired, BigInteger limitSqrtPrice, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SWAP, 
                Arrays.<Type>asList(new Address(160, recipient),
                new Bool(zeroToOne),
                new Int256(amountRequired),
                new Uint160(limitSqrtPrice),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> swapSupportingFeeOnInputTokens(String sender, String recipient, Boolean zeroToOne, BigInteger amountRequired, BigInteger limitSqrtPrice, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SWAPSUPPORTINGFEEONINPUTTOKENS, 
                Arrays.<Type>asList(new Address(160, sender),
                new Address(160, recipient),
                new Bool(zeroToOne),
                new Int256(amountRequired),
                new Uint160(limitSqrtPrice),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> tickSpacing() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKSPACING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int24>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> tickTable(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TICKTABLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int16(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
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

    public RemoteFunctionCall<Tuple7<Boolean, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> timepoints(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TIMEPOINTS, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint32>() {}, new TypeReference<Int56>() {}, new TypeReference<Uint160>() {}, new TypeReference<Uint88>() {}, new TypeReference<Int24>() {}, new TypeReference<Uint144>() {}));
        return new RemoteFunctionCall<Tuple7<Boolean, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple7<Boolean, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<Boolean, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<Boolean, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
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

    public RemoteFunctionCall<BigInteger> totalFeeGrowth0Token() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALFEEGROWTH0TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalFeeGrowth1Token() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALFEEGROWTH1TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static AlgebraPool load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AlgebraPool(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AlgebraPool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AlgebraPool(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AlgebraPool load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AlgebraPool(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AlgebraPool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AlgebraPool(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class BurnEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger bottomTick;

        public BigInteger topTick;

        public BigInteger liquidityAmount;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class CollectEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger bottomTick;

        public BigInteger topTick;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class CommunityFeeEventResponse extends BaseEventResponse {
        public BigInteger communityFee0New;

        public BigInteger communityFee1New;
    }

    public static class FeeEventResponse extends BaseEventResponse {
        public BigInteger fee;
    }

    public static class FlashEventResponse extends BaseEventResponse {
        public String sender;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;

        public BigInteger paid0;

        public BigInteger paid1;
    }

    public static class IncentiveEventResponse extends BaseEventResponse {
        public String virtualPoolAddress;
    }

    public static class InitializeEventResponse extends BaseEventResponse {
        public BigInteger price;

        public BigInteger tick;
    }

    public static class LiquidityCooldownEventResponse extends BaseEventResponse {
        public BigInteger liquidityCooldown;
    }

    public static class MintEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger bottomTick;

        public BigInteger topTick;

        public String sender;

        public BigInteger liquidityAmount;

        public BigInteger amount0;

        public BigInteger amount1;
    }

    public static class SwapEventResponse extends BaseEventResponse {
        public String sender;

        public String recipient;

        public BigInteger amount0;

        public BigInteger amount1;

        public BigInteger price;

        public BigInteger liquidity;

        public BigInteger tick;
    }
}
