package tech.pegasys.aiakos.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class Aiakos extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b5060405160208062001cc48339810180604052602081101561003157600080fd5b8101908080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a361011a336101366401000000000261179b176401000000009004565b60008111151561012957600080fd5b80600281905550506102bf565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515610201576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206181526020017f646472657373000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6119f580620002cf6000396000f3fe6080604052600436106100a9576000357c010000000000000000000000000000000000000000000000000000000090048063441e88d5146100ae5780636b453c1f1461020c578063715018a61461025057806374d748111461026757806379f0e67c146102925780638da5cb5b146102c15780638f32d59b14610318578063bf1a8fe914610347578063dd57366a14610431578063e6a15a481461049a578063f2fde38b1461055f575b600080fd5b3480156100ba57600080fd5b50610174600480360360208110156100d157600080fd5b81019080803590602001906401000000008111156100ee57600080fd5b82018360208201111561010057600080fd5b8035906020019184600183028401116401000000008311171561012257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506105b0565b60405180806020018581526020018415151515815260200183151515158152602001828103825286818151815260200191508051906020019080838360005b838110156101ce5780820151818401526020810190506101b3565b50505050905090810190601f1680156101fb5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b61024e6004803603602081101561022257600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107b0565b005b34801561025c57600080fd5b506102656108c4565b005b34801561027357600080fd5b5061027c6109ff565b6040518082815260200191505060405180910390f35b34801561029e57600080fd5b506102a7610a09565b604051808215151515815260200191505060405180910390f35b3480156102cd57600080fd5b506102d6610a24565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561032457600080fd5b5061032d610a4d565b604051808215151515815260200191505060405180910390f35b34801561035357600080fd5b506104176004803603604081101561036a57600080fd5b810190808035906020019064010000000081111561038757600080fd5b82018360208201111561039957600080fd5b803590602001918460018302840111640100000000831117156103bb57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929080359060200190929190505050610aa4565b604051808215151515815260200191505060405180910390f35b34801561043d57600080fd5b506104806004803603602081101561045457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d2a565b604051808215151515815260200191505060405180910390f35b61055d600480360360408110156104b057600080fd5b81019080803590602001906401000000008111156104cd57600080fd5b8201836020820111156104df57600080fd5b8035906020019184600183028401116401000000008311171561050157600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929080359060200190929190505050610d47565b005b34801561056b57600080fd5b506105ae6004803603602081101561058257600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611114565b005b60606000806000806003866040518082805190602001908083835b6020831015156105f057805182526020820191506020810190506020830392506105cb565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390209050600015158160040160009054906101000a900460ff16151514156106d7576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602e8152602001807f4169616b6f732e67657452656c65617365496e666f3a2052656c65617365206481526020017f6f6573206e6f742065786973742e00000000000000000000000000000000000081525060400191505060405180910390fd5b8060000181600101548260040160009054906101000a900460ff168360040160019054906101000a900460ff16838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107995780601f1061076e57610100808354040283529160200191610799565b820191906000526020600020905b81548152906001019060200180831161077c57829003601f168201915b505050505093509450945094509450509193509193565b6107b8610a4d565b151561082c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415151561086957600080fd5b61087d8260016111da90919063ffffffff16565b8173ffffffffffffffffffffffffffffffffffffffff167f1390a149489c82e9e2e89927bdb17ad1ea3f0cc314fce06c96369721d3afb8f060405160405180910390a25050565b6108cc610a4d565b1515610940576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a360008060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550565b6000600254905090565b6000610a1f3360016112b790919063ffffffff16565b905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614905090565b600080600102821415610b45576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260288152602001807f4169616b6f732e636865636b52656c656173653a20496e76616c696420696e7081526020017f757420686173682e00000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60006003846040518082805190602001908083835b602083101515610b7f5780518252602082019150602081019050602083039250610b5a565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902090508060040160009054906101000a900460ff161515610c60576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602c8152602001807f4169616b6f732e636865636b52656c656173653a2052656c6561736520646f6581526020017f73206e6f742065786973742e000000000000000000000000000000000000000081525060400191505060405180910390fd5b8060040160019054906101000a900460ff161515610d0c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a8152602001807f4169616b6f732e636865636b52656c656173653a2052656c65617365206e6f7481526020017f20617070726f7665642e0000000000000000000000000000000000000000000081525060400191505060405180910390fd5b610d1f83826113da90919063ffffffff16565b600191505092915050565b6000610d408260016112b790919063ffffffff16565b9050919050565b610d5b3360016112b790919063ffffffff16565b1515610df5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f4169616b6f733a2063616c6c6572206973206e6f742061206d61696e7461696e81526020017f65722e000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60006003836040518082805190602001908083835b602083101515610e2f5780518252602082019150602081019050602083039250610e0a565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902090508060040160019054906101000a900460ff1615610f0f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f4169616b6f732e6465706c6f7952656c656173653a2052656c6561736520616c81526020017f726561647920617070726f7665642e000000000000000000000000000000000081525060400191505060405180910390fd5b8060040160009054906101000a900460ff161515610f4157610f3c83838361147f9092919063ffffffff16565b610f55565b610f5482826113da90919063ffffffff16565b5b600080610f6f33600254856114c39092919063ffffffff16565b91509150811561104a577f6b7afa59a11183364ee985a968c13fe627a79c882690d6fe5bc81e9c51e11c103386604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561100e578082015181840152602081019050610ff3565b50505050905090810190601f16801561103b5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b801561110d577f714e14f599e825214360f88d70c3c4af5cd2931ebff7fdbcaae84a13348f236a8360000160405180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156110fe5780601f106110d3576101008083540402835291602001916110fe565b820191906000526020600020905b8154815290600101906020018083116110e157829003601f168201915b50509250505060405180910390a15b5050505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415151561115157600080fd5b611159610a4d565b15156111cd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b6111d682611713565b5050565b6111e482826112b7565b151515611259576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f526f6c65733a206163636f756e7420616c72656164792068617320726f6c650081525060200191505060405180910390fd5b60018260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515611383576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f526f6c65733a206163636f756e7420697320746865207a65726f20616464726581526020017f737300000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b80826001015414151561147b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001807f4169616b6f732e52656c65617365732e636865636b3a204d6973746d6174636881526020017f2072656c65617365206861736865732e0000000000000000000000000000000081525060400191505060405180910390fd5b5050565b60018360040160006101000a81548160ff021916908315150217905550818360000190805190602001906114b4929190611924565b50808360010181905550505050565b60008084600115158160040160019054906101000a900460ff16151514151515611555576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f52656c6561736520616c726561647920617070726f7665642e0000000000000081525060200191505060405180910390fd5b600115158660020160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515141561166a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260468152602001807f4169616b6f732e52656c65617365732e616464417070726f76616c3a204d616981526020017f6e7461696e657220616c726561647920617070726f766564207468697320726581526020017f6c656173652e000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b60018660020160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550600192508560030160008154809291906001019190505550838660030154141561170a5760018660040160016101000a81548160ff021916908315150217905550600191505b50935093915050565b61171b610a4d565b151561178f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b6117988161179b565b50565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515611866576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206181526020017f646472657373000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061196557805160ff1916838001178555611993565b82800160010185558215611993579182015b82811115611992578251825591602001919060010190611977565b5b5090506119a091906119a4565b5090565b6119c691905b808211156119c25760008160009055506001016119aa565b5090565b9056fea165627a7a72305820b645db32140a1344dc5fd1816495bc731876c6b070f1c6decbc4f7d0fe8f8a800029";

    public static final String FUNC_GETRELEASEINFO = "getReleaseInfo";

    public static final String FUNC_ADDMAINTAINER = "addMaintainer";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_GETREQUIREDNUMBEROFMAINTAINERS = "getRequiredNumberOfMaintainers";

    public static final String FUNC_AMIMAINTAINER = "amIMaintainer";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_CHECKRELEASE = "checkRelease";

    public static final String FUNC_ISMAINTAINER = "isMaintainer";

    public static final String FUNC_DEPLOYRELEASE = "deployRelease";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event MAINTAINERADDED_EVENT = new Event("MaintainerAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event APPROVALGRANTED_EVENT = new Event("ApprovalGranted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event RELEASEAPPROVED_EVENT = new Event("ReleaseApproved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected Aiakos(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Aiakos(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Aiakos(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Aiakos(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Tuple4<String, byte[], Boolean, Boolean>> getReleaseInfo(String _version) {
        final Function function = new Function(FUNC_GETRELEASEINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_version)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple4<String, byte[], Boolean, Boolean>>(
                new Callable<Tuple4<String, byte[], Boolean, Boolean>>() {
                    @Override
                    public Tuple4<String, byte[], Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, byte[], Boolean, Boolean>(
                                (String) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addMaintainer(String _maintainerAddress, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ADDMAINTAINER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_maintainerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getRequiredNumberOfMaintainers() {
        final Function function = new Function(FUNC_GETREQUIREDNUMBEROFMAINTAINERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> amIMaintainer() {
        final Function function = new Function(FUNC_AMIMAINTAINER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isOwner() {
        final Function function = new Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> checkRelease(String _version, byte[] _sha256Hash) {
        final Function function = new Function(FUNC_CHECKRELEASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_version), 
                new org.web3j.abi.datatypes.generated.Bytes32(_sha256Hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> isMaintainer(String _maintainerAddress) {
        final Function function = new Function(FUNC_ISMAINTAINER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_maintainerAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> deployRelease(String _version, byte[] _sha256Hash, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPLOYRELEASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_version), 
                new org.web3j.abi.datatypes.generated.Bytes32(_sha256Hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<MaintainerAddedEventResponse> getMaintainerAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MAINTAINERADDED_EVENT, transactionReceipt);
        ArrayList<MaintainerAddedEventResponse> responses = new ArrayList<MaintainerAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MaintainerAddedEventResponse typedResponse = new MaintainerAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.maintainer = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MaintainerAddedEventResponse> maintainerAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, MaintainerAddedEventResponse>() {
            @Override
            public MaintainerAddedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MAINTAINERADDED_EVENT, log);
                MaintainerAddedEventResponse typedResponse = new MaintainerAddedEventResponse();
                typedResponse.log = log;
                typedResponse.maintainer = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MaintainerAddedEventResponse> maintainerAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MAINTAINERADDED_EVENT));
        return maintainerAddedEventFlowable(filter);
    }

    public List<ApprovalGrantedEventResponse> getApprovalGrantedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALGRANTED_EVENT, transactionReceipt);
        ArrayList<ApprovalGrantedEventResponse> responses = new ArrayList<ApprovalGrantedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalGrantedEventResponse typedResponse = new ApprovalGrantedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.maintainer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.version = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalGrantedEventResponse> approvalGrantedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalGrantedEventResponse>() {
            @Override
            public ApprovalGrantedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALGRANTED_EVENT, log);
                ApprovalGrantedEventResponse typedResponse = new ApprovalGrantedEventResponse();
                typedResponse.log = log;
                typedResponse.maintainer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.version = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalGrantedEventResponse> approvalGrantedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALGRANTED_EVENT));
        return approvalGrantedEventFlowable(filter);
    }

    public List<ReleaseApprovedEventResponse> getReleaseApprovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RELEASEAPPROVED_EVENT, transactionReceipt);
        ArrayList<ReleaseApprovedEventResponse> responses = new ArrayList<ReleaseApprovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReleaseApprovedEventResponse typedResponse = new ReleaseApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.version = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReleaseApprovedEventResponse> releaseApprovedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ReleaseApprovedEventResponse>() {
            @Override
            public ReleaseApprovedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RELEASEAPPROVED_EVENT, log);
                ReleaseApprovedEventResponse typedResponse = new ReleaseApprovedEventResponse();
                typedResponse.log = log;
                typedResponse.version = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ReleaseApprovedEventResponse> releaseApprovedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RELEASEAPPROVED_EVENT));
        return releaseApprovedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
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

    @Deprecated
    public static Aiakos load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Aiakos(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Aiakos load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Aiakos(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Aiakos load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Aiakos(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Aiakos load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Aiakos(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Aiakos> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _requiredNumberOfMaintainers) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_requiredNumberOfMaintainers)));
        return deployRemoteCall(Aiakos.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Aiakos> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _requiredNumberOfMaintainers) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_requiredNumberOfMaintainers)));
        return deployRemoteCall(Aiakos.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Aiakos> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _requiredNumberOfMaintainers) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_requiredNumberOfMaintainers)));
        return deployRemoteCall(Aiakos.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Aiakos> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _requiredNumberOfMaintainers) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_requiredNumberOfMaintainers)));
        return deployRemoteCall(Aiakos.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class MaintainerAddedEventResponse {
        public Log log;

        public String maintainer;
    }

    public static class ApprovalGrantedEventResponse {
        public Log log;

        public String maintainer;

        public String version;
    }

    public static class ReleaseApprovedEventResponse {
        public Log log;

        public String version;
    }

    public static class OwnershipTransferredEventResponse {
        public Log log;

        public String previousOwner;

        public String newOwner;
    }
}
