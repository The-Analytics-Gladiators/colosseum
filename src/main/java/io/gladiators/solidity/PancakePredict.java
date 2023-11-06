package io.gladiators.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple14;
import org.web3j.tuples.generated.Tuple3;
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
public class PancakePredict extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_MAX_TREASURY_FEE = "MAX_TREASURY_FEE";

    public static final String FUNC_ADMINADDRESS = "adminAddress";

    public static final String FUNC_BETBEAR = "betBear";

    public static final String FUNC_BETBULL = "betBull";

    public static final String FUNC_BUFFERSECONDS = "bufferSeconds";

    public static final String FUNC_CLAIM = "claim";

    public static final String FUNC_CLAIMTREASURY = "claimTreasury";

    public static final String FUNC_CLAIMABLE = "claimable";

    public static final String FUNC_CURRENTEPOCH = "currentEpoch";

    public static final String FUNC_EXECUTEROUND = "executeRound";

    public static final String FUNC_GENESISLOCKONCE = "genesisLockOnce";

    public static final String FUNC_GENESISLOCKROUND = "genesisLockRound";

    public static final String FUNC_GENESISSTARTONCE = "genesisStartOnce";

    public static final String FUNC_GENESISSTARTROUND = "genesisStartRound";

    public static final String FUNC_GETUSERROUNDS = "getUserRounds";

    public static final String FUNC_GETUSERROUNDSLENGTH = "getUserRoundsLength";

    public static final String FUNC_INTERVALSECONDS = "intervalSeconds";

    public static final String FUNC_LEDGER = "ledger";

    public static final String FUNC_MINBETAMOUNT = "minBetAmount";

    public static final String FUNC_OPERATORADDRESS = "operatorAddress";

    public static final String FUNC_ORACLE = "oracle";

    public static final String FUNC_ORACLELATESTROUNDID = "oracleLatestRoundId";

    public static final String FUNC_ORACLEUPDATEALLOWANCE = "oracleUpdateAllowance";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_RECOVERTOKEN = "recoverToken";

    public static final String FUNC_REFUNDABLE = "refundable";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_ROUNDS = "rounds";

    public static final String FUNC_SETADMIN = "setAdmin";

    public static final String FUNC_SETBUFFERANDINTERVALSECONDS = "setBufferAndIntervalSeconds";

    public static final String FUNC_SETMINBETAMOUNT = "setMinBetAmount";

    public static final String FUNC_SETOPERATOR = "setOperator";

    public static final String FUNC_SETORACLE = "setOracle";

    public static final String FUNC_SETORACLEUPDATEALLOWANCE = "setOracleUpdateAllowance";

    public static final String FUNC_SETTREASURYFEE = "setTreasuryFee";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_TREASURYAMOUNT = "treasuryAmount";

    public static final String FUNC_TREASURYFEE = "treasuryFee";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final String FUNC_USERROUNDS = "userRounds";

    public static final Event BETBEAR_EVENT = new Event("BetBear", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event BETBULL_EVENT = new Event("BetBull", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CLAIM_EVENT = new Event("Claim", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ENDROUND_EVENT = new Event("EndRound", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Int256>() {}));
    ;

    public static final Event LOCKROUND_EVENT = new Event("LockRound", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Int256>() {}));
    ;

    public static final Event NEWADMINADDRESS_EVENT = new Event("NewAdminAddress", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event NEWBUFFERANDINTERVALSECONDS_EVENT = new Event("NewBufferAndIntervalSeconds", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NEWMINBETAMOUNT_EVENT = new Event("NewMinBetAmount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NEWOPERATORADDRESS_EVENT = new Event("NewOperatorAddress", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event NEWORACLE_EVENT = new Event("NewOracle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event NEWORACLEUPDATEALLOWANCE_EVENT = new Event("NewOracleUpdateAllowance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event NEWTREASURYFEE_EVENT = new Event("NewTreasuryFee", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAUSE_EVENT = new Event("Pause", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event PAUSED_EVENT = new Event("Paused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event REWARDSCALCULATED_EVENT = new Event("RewardsCalculated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event STARTROUND_EVENT = new Event("StartRound", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TOKENRECOVERY_EVENT = new Event("TokenRecovery", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TREASURYCLAIM_EVENT = new Event("TreasuryClaim", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event UNPAUSE_EVENT = new Event("Unpause", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UNPAUSED_EVENT = new Event("Unpaused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected PancakePredict(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PancakePredict(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PancakePredict(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PancakePredict(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BetBearEventResponse> getBetBearEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BETBEAR_EVENT, transactionReceipt);
        ArrayList<BetBearEventResponse> responses = new ArrayList<BetBearEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BetBearEventResponse typedResponse = new BetBearEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BetBearEventResponse> betBearEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BetBearEventResponse>() {
            @Override
            public BetBearEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BETBEAR_EVENT, log);
                BetBearEventResponse typedResponse = new BetBearEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BetBearEventResponse> betBearEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BETBEAR_EVENT));
        return betBearEventFlowable(filter);
    }

    public static List<BetBullEventResponse> getBetBullEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BETBULL_EVENT, transactionReceipt);
        ArrayList<BetBullEventResponse> responses = new ArrayList<BetBullEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BetBullEventResponse typedResponse = new BetBullEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BetBullEventResponse> betBullEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BetBullEventResponse>() {
            @Override
            public BetBullEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BETBULL_EVENT, log);
                BetBullEventResponse typedResponse = new BetBullEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BetBullEventResponse> betBullEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BETBULL_EVENT));
        return betBullEventFlowable(filter);
    }

    public static List<ClaimEventResponse> getClaimEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CLAIM_EVENT, transactionReceipt);
        ArrayList<ClaimEventResponse> responses = new ArrayList<ClaimEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ClaimEventResponse typedResponse = new ClaimEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ClaimEventResponse> claimEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ClaimEventResponse>() {
            @Override
            public ClaimEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CLAIM_EVENT, log);
                ClaimEventResponse typedResponse = new ClaimEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ClaimEventResponse> claimEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLAIM_EVENT));
        return claimEventFlowable(filter);
    }

    public static List<EndRoundEventResponse> getEndRoundEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ENDROUND_EVENT, transactionReceipt);
        ArrayList<EndRoundEventResponse> responses = new ArrayList<EndRoundEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            EndRoundEventResponse typedResponse = new EndRoundEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.roundId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EndRoundEventResponse> endRoundEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, EndRoundEventResponse>() {
            @Override
            public EndRoundEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ENDROUND_EVENT, log);
                EndRoundEventResponse typedResponse = new EndRoundEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.roundId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EndRoundEventResponse> endRoundEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ENDROUND_EVENT));
        return endRoundEventFlowable(filter);
    }

    public static List<LockRoundEventResponse> getLockRoundEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(LOCKROUND_EVENT, transactionReceipt);
        ArrayList<LockRoundEventResponse> responses = new ArrayList<LockRoundEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            LockRoundEventResponse typedResponse = new LockRoundEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.roundId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LockRoundEventResponse> lockRoundEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LockRoundEventResponse>() {
            @Override
            public LockRoundEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(LOCKROUND_EVENT, log);
                LockRoundEventResponse typedResponse = new LockRoundEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.roundId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LockRoundEventResponse> lockRoundEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOCKROUND_EVENT));
        return lockRoundEventFlowable(filter);
    }

    public static List<NewAdminAddressEventResponse> getNewAdminAddressEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWADMINADDRESS_EVENT, transactionReceipt);
        ArrayList<NewAdminAddressEventResponse> responses = new ArrayList<NewAdminAddressEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewAdminAddressEventResponse typedResponse = new NewAdminAddressEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.admin = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewAdminAddressEventResponse> newAdminAddressEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewAdminAddressEventResponse>() {
            @Override
            public NewAdminAddressEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWADMINADDRESS_EVENT, log);
                NewAdminAddressEventResponse typedResponse = new NewAdminAddressEventResponse();
                typedResponse.log = log;
                typedResponse.admin = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewAdminAddressEventResponse> newAdminAddressEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWADMINADDRESS_EVENT));
        return newAdminAddressEventFlowable(filter);
    }

    public static List<NewBufferAndIntervalSecondsEventResponse> getNewBufferAndIntervalSecondsEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWBUFFERANDINTERVALSECONDS_EVENT, transactionReceipt);
        ArrayList<NewBufferAndIntervalSecondsEventResponse> responses = new ArrayList<NewBufferAndIntervalSecondsEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewBufferAndIntervalSecondsEventResponse typedResponse = new NewBufferAndIntervalSecondsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.bufferSeconds = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.intervalSeconds = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewBufferAndIntervalSecondsEventResponse> newBufferAndIntervalSecondsEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewBufferAndIntervalSecondsEventResponse>() {
            @Override
            public NewBufferAndIntervalSecondsEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWBUFFERANDINTERVALSECONDS_EVENT, log);
                NewBufferAndIntervalSecondsEventResponse typedResponse = new NewBufferAndIntervalSecondsEventResponse();
                typedResponse.log = log;
                typedResponse.bufferSeconds = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.intervalSeconds = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewBufferAndIntervalSecondsEventResponse> newBufferAndIntervalSecondsEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWBUFFERANDINTERVALSECONDS_EVENT));
        return newBufferAndIntervalSecondsEventFlowable(filter);
    }

    public static List<NewMinBetAmountEventResponse> getNewMinBetAmountEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWMINBETAMOUNT_EVENT, transactionReceipt);
        ArrayList<NewMinBetAmountEventResponse> responses = new ArrayList<NewMinBetAmountEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewMinBetAmountEventResponse typedResponse = new NewMinBetAmountEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.minBetAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewMinBetAmountEventResponse> newMinBetAmountEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewMinBetAmountEventResponse>() {
            @Override
            public NewMinBetAmountEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWMINBETAMOUNT_EVENT, log);
                NewMinBetAmountEventResponse typedResponse = new NewMinBetAmountEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.minBetAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewMinBetAmountEventResponse> newMinBetAmountEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWMINBETAMOUNT_EVENT));
        return newMinBetAmountEventFlowable(filter);
    }

    public static List<NewOperatorAddressEventResponse> getNewOperatorAddressEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWOPERATORADDRESS_EVENT, transactionReceipt);
        ArrayList<NewOperatorAddressEventResponse> responses = new ArrayList<NewOperatorAddressEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewOperatorAddressEventResponse typedResponse = new NewOperatorAddressEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewOperatorAddressEventResponse> newOperatorAddressEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewOperatorAddressEventResponse>() {
            @Override
            public NewOperatorAddressEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWOPERATORADDRESS_EVENT, log);
                NewOperatorAddressEventResponse typedResponse = new NewOperatorAddressEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewOperatorAddressEventResponse> newOperatorAddressEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWOPERATORADDRESS_EVENT));
        return newOperatorAddressEventFlowable(filter);
    }

    public static List<NewOracleEventResponse> getNewOracleEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWORACLE_EVENT, transactionReceipt);
        ArrayList<NewOracleEventResponse> responses = new ArrayList<NewOracleEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewOracleEventResponse typedResponse = new NewOracleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oracle = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewOracleEventResponse> newOracleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewOracleEventResponse>() {
            @Override
            public NewOracleEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWORACLE_EVENT, log);
                NewOracleEventResponse typedResponse = new NewOracleEventResponse();
                typedResponse.log = log;
                typedResponse.oracle = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewOracleEventResponse> newOracleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWORACLE_EVENT));
        return newOracleEventFlowable(filter);
    }

    public static List<NewOracleUpdateAllowanceEventResponse> getNewOracleUpdateAllowanceEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWORACLEUPDATEALLOWANCE_EVENT, transactionReceipt);
        ArrayList<NewOracleUpdateAllowanceEventResponse> responses = new ArrayList<NewOracleUpdateAllowanceEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewOracleUpdateAllowanceEventResponse typedResponse = new NewOracleUpdateAllowanceEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oracleUpdateAllowance = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewOracleUpdateAllowanceEventResponse> newOracleUpdateAllowanceEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewOracleUpdateAllowanceEventResponse>() {
            @Override
            public NewOracleUpdateAllowanceEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWORACLEUPDATEALLOWANCE_EVENT, log);
                NewOracleUpdateAllowanceEventResponse typedResponse = new NewOracleUpdateAllowanceEventResponse();
                typedResponse.log = log;
                typedResponse.oracleUpdateAllowance = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewOracleUpdateAllowanceEventResponse> newOracleUpdateAllowanceEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWORACLEUPDATEALLOWANCE_EVENT));
        return newOracleUpdateAllowanceEventFlowable(filter);
    }

    public static List<NewTreasuryFeeEventResponse> getNewTreasuryFeeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWTREASURYFEE_EVENT, transactionReceipt);
        ArrayList<NewTreasuryFeeEventResponse> responses = new ArrayList<NewTreasuryFeeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewTreasuryFeeEventResponse typedResponse = new NewTreasuryFeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.treasuryFee = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewTreasuryFeeEventResponse> newTreasuryFeeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewTreasuryFeeEventResponse>() {
            @Override
            public NewTreasuryFeeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWTREASURYFEE_EVENT, log);
                NewTreasuryFeeEventResponse typedResponse = new NewTreasuryFeeEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.treasuryFee = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewTreasuryFeeEventResponse> newTreasuryFeeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWTREASURYFEE_EVENT));
        return newTreasuryFeeEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<PauseEventResponse> getPauseEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAUSE_EVENT, transactionReceipt);
        ArrayList<PauseEventResponse> responses = new ArrayList<PauseEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PauseEventResponse typedResponse = new PauseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PauseEventResponse> pauseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PauseEventResponse>() {
            @Override
            public PauseEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSE_EVENT, log);
                PauseEventResponse typedResponse = new PauseEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PauseEventResponse> pauseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSE_EVENT));
        return pauseEventFlowable(filter);
    }

    public static List<PausedEventResponse> getPausedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAUSED_EVENT, transactionReceipt);
        ArrayList<PausedEventResponse> responses = new ArrayList<PausedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PausedEventResponse typedResponse = new PausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PausedEventResponse>() {
            @Override
            public PausedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSED_EVENT, log);
                PausedEventResponse typedResponse = new PausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSED_EVENT));
        return pausedEventFlowable(filter);
    }

    public static List<RewardsCalculatedEventResponse> getRewardsCalculatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REWARDSCALCULATED_EVENT, transactionReceipt);
        ArrayList<RewardsCalculatedEventResponse> responses = new ArrayList<RewardsCalculatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RewardsCalculatedEventResponse typedResponse = new RewardsCalculatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.rewardBaseCalAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.rewardAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.treasuryAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RewardsCalculatedEventResponse> rewardsCalculatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RewardsCalculatedEventResponse>() {
            @Override
            public RewardsCalculatedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REWARDSCALCULATED_EVENT, log);
                RewardsCalculatedEventResponse typedResponse = new RewardsCalculatedEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.rewardBaseCalAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.rewardAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.treasuryAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RewardsCalculatedEventResponse> rewardsCalculatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REWARDSCALCULATED_EVENT));
        return rewardsCalculatedEventFlowable(filter);
    }

    public static List<StartRoundEventResponse> getStartRoundEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STARTROUND_EVENT, transactionReceipt);
        ArrayList<StartRoundEventResponse> responses = new ArrayList<StartRoundEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            StartRoundEventResponse typedResponse = new StartRoundEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<StartRoundEventResponse> startRoundEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, StartRoundEventResponse>() {
            @Override
            public StartRoundEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(STARTROUND_EVENT, log);
                StartRoundEventResponse typedResponse = new StartRoundEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<StartRoundEventResponse> startRoundEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STARTROUND_EVENT));
        return startRoundEventFlowable(filter);
    }

    public static List<TokenRecoveryEventResponse> getTokenRecoveryEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TOKENRECOVERY_EVENT, transactionReceipt);
        ArrayList<TokenRecoveryEventResponse> responses = new ArrayList<TokenRecoveryEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TokenRecoveryEventResponse typedResponse = new TokenRecoveryEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokenRecoveryEventResponse> tokenRecoveryEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokenRecoveryEventResponse>() {
            @Override
            public TokenRecoveryEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENRECOVERY_EVENT, log);
                TokenRecoveryEventResponse typedResponse = new TokenRecoveryEventResponse();
                typedResponse.log = log;
                typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TokenRecoveryEventResponse> tokenRecoveryEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENRECOVERY_EVENT));
        return tokenRecoveryEventFlowable(filter);
    }

    public static List<TreasuryClaimEventResponse> getTreasuryClaimEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TREASURYCLAIM_EVENT, transactionReceipt);
        ArrayList<TreasuryClaimEventResponse> responses = new ArrayList<TreasuryClaimEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TreasuryClaimEventResponse typedResponse = new TreasuryClaimEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TreasuryClaimEventResponse> treasuryClaimEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TreasuryClaimEventResponse>() {
            @Override
            public TreasuryClaimEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TREASURYCLAIM_EVENT, log);
                TreasuryClaimEventResponse typedResponse = new TreasuryClaimEventResponse();
                typedResponse.log = log;
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TreasuryClaimEventResponse> treasuryClaimEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TREASURYCLAIM_EVENT));
        return treasuryClaimEventFlowable(filter);
    }

    public static List<UnpauseEventResponse> getUnpauseEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UNPAUSE_EVENT, transactionReceipt);
        ArrayList<UnpauseEventResponse> responses = new ArrayList<UnpauseEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UnpauseEventResponse typedResponse = new UnpauseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpauseEventResponse> unpauseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UnpauseEventResponse>() {
            @Override
            public UnpauseEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSE_EVENT, log);
                UnpauseEventResponse typedResponse = new UnpauseEventResponse();
                typedResponse.log = log;
                typedResponse.epoch = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UnpauseEventResponse> unpauseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSE_EVENT));
        return unpauseEventFlowable(filter);
    }

    public static List<UnpausedEventResponse> getUnpausedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UNPAUSED_EVENT, transactionReceipt);
        ArrayList<UnpausedEventResponse> responses = new ArrayList<UnpausedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UnpausedEventResponse typedResponse = new UnpausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UnpausedEventResponse>() {
            @Override
            public UnpausedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSED_EVENT, log);
                UnpausedEventResponse typedResponse = new UnpausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSED_EVENT));
        return unpausedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> MAX_TREASURY_FEE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MAX_TREASURY_FEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> adminAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADMINADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> betBear(BigInteger epoch, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BETBEAR, 
                Arrays.<Type>asList(new Uint256(epoch)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> betBull(BigInteger epoch, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BETBULL, 
                Arrays.<Type>asList(new Uint256(epoch)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> bufferSeconds() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BUFFERSECONDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> claim(List<BigInteger> epochs) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CLAIM, 
                Arrays.<Type>asList(new DynamicArray<Uint256>(
                        Uint256.class,
                        org.web3j.abi.Utils.typeMap(epochs, Uint256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> claimTreasury() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CLAIMTREASURY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> claimable(BigInteger epoch, String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CLAIMABLE, 
                Arrays.<Type>asList(new Uint256(epoch),
                new Address(160, user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> currentEpoch() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CURRENTEPOCH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> executeRound() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EXECUTEROUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> genesisLockOnce() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GENESISLOCKONCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> genesisLockRound() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GENESISLOCKROUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> genesisStartOnce() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GENESISSTARTONCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> genesisStartRound() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GENESISSTARTROUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<List<BigInteger>, List<BetInfo>, BigInteger>> getUserRounds(String user, BigInteger cursor, BigInteger size) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERROUNDS, 
                Arrays.<Type>asList(new Address(160, user),
                new Uint256(cursor),
                new Uint256(size)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<BetInfo>>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<List<BigInteger>, List<BetInfo>, BigInteger>>(function,
                new Callable<Tuple3<List<BigInteger>, List<BetInfo>, BigInteger>>() {
                    @Override
                    public Tuple3<List<BigInteger>, List<BetInfo>, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<BigInteger>, List<BetInfo>, BigInteger>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<BetInfo>) results.get(1).getValue()), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getUserRoundsLength(String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERROUNDSLENGTH, 
                Arrays.<Type>asList(new Address(160, user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> intervalSeconds() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_INTERVALSECONDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, Boolean>> ledger(BigInteger param0, String param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LEDGER, 
                Arrays.<Type>asList(new Uint256(param0),
                new Address(160, param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> minBetAmount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MINBETAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> operatorAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OPERATORADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> oracle() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORACLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> oracleLatestRoundId() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORACLELATESTROUNDID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> oracleUpdateAllowance() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ORACLEUPDATEALLOWANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> recoverToken(String _token, BigInteger _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RECOVERTOKEN, 
                Arrays.<Type>asList(new Address(160, _token),
                new Uint256(_amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> refundable(BigInteger epoch, String user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REFUNDABLE, 
                Arrays.<Type>asList(new Uint256(epoch),
                new Address(160, user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple14<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>> rounds(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ROUNDS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple14<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple14<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple14<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple14<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue(), 
                                (BigInteger) results.get(9).getValue(), 
                                (BigInteger) results.get(10).getValue(), 
                                (BigInteger) results.get(11).getValue(), 
                                (BigInteger) results.get(12).getValue(), 
                                (Boolean) results.get(13).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> setAdmin(String _adminAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETADMIN, 
                Arrays.<Type>asList(new Address(160, _adminAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setBufferAndIntervalSeconds(BigInteger _bufferSeconds, BigInteger _intervalSeconds) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBUFFERANDINTERVALSECONDS, 
                Arrays.<Type>asList(new Uint256(_bufferSeconds),
                new Uint256(_intervalSeconds)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMinBetAmount(BigInteger _minBetAmount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMINBETAMOUNT, 
                Arrays.<Type>asList(new Uint256(_minBetAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOperator(String _operatorAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETOPERATOR, 
                Arrays.<Type>asList(new Address(160, _operatorAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOracle(String _oracle) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETORACLE, 
                Arrays.<Type>asList(new Address(160, _oracle)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOracleUpdateAllowance(BigInteger _oracleUpdateAllowance) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETORACLEUPDATEALLOWANCE, 
                Arrays.<Type>asList(new Uint256(_oracleUpdateAllowance)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTreasuryFee(BigInteger _treasuryFee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETTREASURYFEE, 
                Arrays.<Type>asList(new Uint256(_treasuryFee)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> treasuryAmount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TREASURYAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> treasuryFee() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TREASURYFEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> userRounds(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_USERROUNDS, 
                Arrays.<Type>asList(new Address(160, param0),
                new Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static PancakePredict load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakePredict(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PancakePredict load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PancakePredict(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PancakePredict load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PancakePredict(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PancakePredict load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PancakePredict(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class BetInfo extends StaticStruct {
        public BigInteger position;

        public BigInteger amount;

        public Boolean claimed;

        public BetInfo(BigInteger position, BigInteger amount, Boolean claimed) {
            super(new Uint8(position),
                    new Uint256(amount),
                    new Bool(claimed));
            this.position = position;
            this.amount = amount;
            this.claimed = claimed;
        }

        public BetInfo(Uint8 position, Uint256 amount, Bool claimed) {
            super(position, amount, claimed);
            this.position = position.getValue();
            this.amount = amount.getValue();
            this.claimed = claimed.getValue();
        }
    }

    public static class BetBearEventResponse extends BaseEventResponse {
        public String sender;

        public BigInteger epoch;

        public BigInteger amount;
    }

    public static class BetBullEventResponse extends BaseEventResponse {
        public String sender;

        public BigInteger epoch;

        public BigInteger amount;
    }

    public static class ClaimEventResponse extends BaseEventResponse {
        public String sender;

        public BigInteger epoch;

        public BigInteger amount;
    }

    public static class EndRoundEventResponse extends BaseEventResponse {
        public BigInteger epoch;

        public BigInteger roundId;

        public BigInteger price;
    }

    public static class LockRoundEventResponse extends BaseEventResponse {
        public BigInteger epoch;

        public BigInteger roundId;

        public BigInteger price;
    }

    public static class NewAdminAddressEventResponse extends BaseEventResponse {
        public String admin;
    }

    public static class NewBufferAndIntervalSecondsEventResponse extends BaseEventResponse {
        public BigInteger bufferSeconds;

        public BigInteger intervalSeconds;
    }

    public static class NewMinBetAmountEventResponse extends BaseEventResponse {
        public BigInteger epoch;

        public BigInteger minBetAmount;
    }

    public static class NewOperatorAddressEventResponse extends BaseEventResponse {
        public String operator;
    }

    public static class NewOracleEventResponse extends BaseEventResponse {
        public String oracle;
    }

    public static class NewOracleUpdateAllowanceEventResponse extends BaseEventResponse {
        public BigInteger oracleUpdateAllowance;
    }

    public static class NewTreasuryFeeEventResponse extends BaseEventResponse {
        public BigInteger epoch;

        public BigInteger treasuryFee;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PauseEventResponse extends BaseEventResponse {
        public BigInteger epoch;
    }

    public static class PausedEventResponse extends BaseEventResponse {
        public String account;
    }

    public static class RewardsCalculatedEventResponse extends BaseEventResponse {
        public BigInteger epoch;

        public BigInteger rewardBaseCalAmount;

        public BigInteger rewardAmount;

        public BigInteger treasuryAmount;
    }

    public static class StartRoundEventResponse extends BaseEventResponse {
        public BigInteger epoch;
    }

    public static class TokenRecoveryEventResponse extends BaseEventResponse {
        public String token;

        public BigInteger amount;
    }

    public static class TreasuryClaimEventResponse extends BaseEventResponse {
        public BigInteger amount;
    }

    public static class UnpauseEventResponse extends BaseEventResponse {
        public BigInteger epoch;
    }

    public static class UnpausedEventResponse extends BaseEventResponse {
        public String account;
    }
}
