package com.example.stardust.Trade;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
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
 * <p>Generated with web3j version 4.9.8.
 */
public class SteelPlateContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610749806100206000396000f3fe60806040526004361061003f5760003560e01c806315156b51146100445780631668820414610059578063b9b4499a14610079578063c8c55ae2146100b4575b600080fd5b610057610052366004610503565b6100d6565b005b34801561006557600080fd5b50610057610074366004610453565b6101df565b34801561008557600080fd5b50610099610094366004610503565b6102e9565b6040516100ab96959493929190610633565b60405180910390f35b3480156100c057600080fd5b506100c96103b4565b6040516100ab91906105d4565b600081815260208190526040902060058101546001600160a01b0316156101185760405162461bcd60e51b815260040161010f9061059d565b60405180910390fd5b8060020154341461013b5760405162461bcd60e51b815260040161010f90610566565b6005810180546001600160a01b031916331790556003810180549060006101618361067a565b909155505060048101546040516001600160a01b03909116903480156108fc02916000818181858888f193505050501580156101a1573d6000803e3d6000fd5b507f77e811b4d94ddea66ea1ba43f8609490f134ec1284c521cb5216317e3e04e14782336040516101d39291906105dd565b60405180910390a15050565b600180549060006101ef836106cc565b90915550506040805160c08101825260018054808352602080840188815284860188905260608501879052336080860152600060a0860181905292835282825294909120835181559351805193949361024f9385019291909101906103ba565b5060408281015160028301556060830151600383015560808301516004830180546001600160a01b03199081166001600160a01b039384161790915560a0909401516005909301805490941692169190911790915560015490517f1bb54bc25c8dba063eabbcf86205b84a8ddba2f6cb1479fe00f846d5c8437468916102dc9186908690869033906105f4565b60405180910390a1505050565b6000602081905290815260409020805460018201805491929161030b90610691565b80601f016020809104026020016040519081016040528092919081815260200182805461033790610691565b80156103845780601f1061035957610100808354040283529160200191610384565b820191906000526020600020905b81548152906001019060200180831161036757829003601f168201915b505050600284015460038501546004860154600590960154949591949093506001600160a01b0391821692501686565b60015481565b8280546103c690610691565b90600052602060002090601f0160209004810192826103e8576000855561042e565b82601f1061040157805160ff191683800117855561042e565b8280016001018555821561042e579182015b8281111561042e578251825591602001919060010190610413565b5061043a92915061043e565b5090565b5b8082111561043a576000815560010161043f565b600080600060608486031215610467578283fd5b833567ffffffffffffffff8082111561047e578485fd5b818601915086601f830112610491578485fd5b81356020828211156104a5576104a56106fd565b604051601f8301601f19168101820184811182821017156104c8576104c86106fd565b60405282815284830182018a10156104de578788fd5b8282860183830137918201810196909652979486013596505050604090930135925050565b600060208284031215610514578081fd5b5035919050565b60008151808452815b8181101561054057602081850181015186830182015201610524565b818111156105515782602083870101525b50601f01601f19169290920160200192915050565b6020808252601e908201527f496e636f727265637420616d6f756e74206f662065746865722073656e740000604082015260600190565b60208082526018908201527f537465656c20706c61746520616c726561647920736f6c640000000000000000604082015260600190565b90815260200190565b9182526001600160a01b0316602082015260400190565b600086825260a0602083015261060d60a083018761051b565b60408301959095525060608101929092526001600160a01b031660809091015292915050565b600087825260c0602083015261064c60c083018861051b565b60408301969096525060608101939093526001600160a01b0391821660808401521660a09091015292915050565b600081610689576106896106e7565b506000190190565b6002810460018216806106a557607f821691505b602082108114156106c657634e487b7160e01b600052602260045260246000fd5b50919050565b60006000198214156106e0576106e06106e7565b5060010190565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052604160045260246000fdfea26469706673582212208962b541be8d6a74f9a912637bef5d42fdf536313c07656e7078c321130cf26164736f6c63430008000033";
    //添加干干
    public static final String FUNC_ADDSTEELPLATE = "addSteelPlate";
    //购买
    public static final String FUNC_BUYSTEELPLATE = "buySteelPlate";
    //钢板计数
    public static final String FUNC_STEELPLATECOUNT = "steelPlateCount";
    //钢板
    public static final String FUNC_STEELPLATES = "steelPlates";

    public static final Event STEELPLATEADDED_EVENT = new Event("SteelPlateAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
            }, new TypeReference<Utf8String>() {
            }, new TypeReference<Uint256>() {
            }, new TypeReference<Uint256>() {
            }, new TypeReference<Address>() {
            }));
    ;

    public static final Event STEELPLATEBOUGHT_EVENT = new Event("SteelPlateBought",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
            }, new TypeReference<Address>() {
            }));
    ;

    @Deprecated
    protected SteelPlateContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SteelPlateContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SteelPlateContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SteelPlateContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<SteelPlateAddedEventResponse> getSteelPlateAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STEELPLATEADDED_EVENT, transactionReceipt);
        ArrayList<SteelPlateAddedEventResponse> responses = new ArrayList<SteelPlateAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SteelPlateAddedEventResponse typedResponse = new SteelPlateAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.quantity = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SteelPlateAddedEventResponse getSteelPlateAddedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STEELPLATEADDED_EVENT, log);
        SteelPlateAddedEventResponse typedResponse = new SteelPlateAddedEventResponse();
        typedResponse.log = log;
        typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.quantity = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.seller = (String) eventValues.getNonIndexedValues().get(4).getValue();
        return typedResponse;
    }

    public Flowable<SteelPlateAddedEventResponse> steelPlateAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSteelPlateAddedEventFromLog(log));
    }

    public Flowable<SteelPlateAddedEventResponse> steelPlateAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STEELPLATEADDED_EVENT));
        return steelPlateAddedEventFlowable(filter);
    }

    public static List<SteelPlateBoughtEventResponse> getSteelPlateBoughtEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STEELPLATEBOUGHT_EVENT, transactionReceipt);
        ArrayList<SteelPlateBoughtEventResponse> responses = new ArrayList<SteelPlateBoughtEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SteelPlateBoughtEventResponse typedResponse = new SteelPlateBoughtEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SteelPlateBoughtEventResponse getSteelPlateBoughtEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STEELPLATEBOUGHT_EVENT, log);
        SteelPlateBoughtEventResponse typedResponse = new SteelPlateBoughtEventResponse();
        typedResponse.log = log;
        typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<SteelPlateBoughtEventResponse> steelPlateBoughtEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSteelPlateBoughtEventFromLog(log));
    }

    public Flowable<SteelPlateBoughtEventResponse> steelPlateBoughtEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STEELPLATEBOUGHT_EVENT));
        return steelPlateBoughtEventFlowable(filter);
    }

    //添加商品 需要参数 商品名 商品价 商品数量
    public RemoteFunctionCall<TransactionReceipt> addSteelPlate(String name, BigInteger price, BigInteger quantity) {
        final Function function = new Function(
                FUNC_ADDSTEELPLATE,
                Arrays.<Type>asList(new Utf8String(name),
                        new Uint256(price),
                        new Uint256(quantity)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    //购买商品 商品id 商品价钱
    public RemoteFunctionCall<TransactionReceipt> buySteelPlate(BigInteger id, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUYSTEELPLATE,
                Arrays.<Type>asList(new Uint256(id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    //钢板计数
    public RemoteFunctionCall<BigInteger> steelPlateCount() {
        final Function function = new Function(FUNC_STEELPLATECOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, BigInteger, String, String>> steelPlates(BigInteger param0) {
        final Function function = new Function(FUNC_STEELPLATES,
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }, new TypeReference<Utf8String>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Uint256>() {
                }, new TypeReference<Address>() {
                }, new TypeReference<Address>() {
                }));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, BigInteger, String, String>>(function,
                new Callable<Tuple6<BigInteger, String, BigInteger, BigInteger, String, String>>() {
                    @Override
                    public Tuple6<BigInteger, String, BigInteger, BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, BigInteger, BigInteger, String, String>(
                                (BigInteger) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (String) results.get(4).getValue(),
                                (String) results.get(5).getValue());
                    }
                });
    }

    @Deprecated
    public static SteelPlateContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SteelPlateContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SteelPlateContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SteelPlateContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SteelPlateContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SteelPlateContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SteelPlateContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SteelPlateContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SteelPlateContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SteelPlateContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SteelPlateContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SteelPlateContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SteelPlateContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SteelPlateContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SteelPlateContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SteelPlateContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class SteelPlateAddedEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String name;

        public BigInteger price;

        public BigInteger quantity;

        public String seller;
    }

    public static class SteelPlateBoughtEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String buyer;
    }
}
