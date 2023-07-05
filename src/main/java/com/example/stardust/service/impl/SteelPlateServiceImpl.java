package com.example.stardust.service.impl;

import com.example.stardust.Trade.SteelPlateContract;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.math.BigInteger;

/**
 * @author AlHeae
 * @Description
 * @date 2023/5/1 23:20
 */
@Service
public class SteelPlateServiceImpl {

    Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
    /**
     * @Description 私钥
     */
    Credentials credentials = Credentials.create("4b2131582338d8e33b27157002835d99b5302e309ee41b621f55ce87ecbe0449");
    /**
     * @Description 合约地址
     */
    String CONTRACT_ADDRESS = "0x94B508f8b54d30A7Bc49E1601E8BdFe15FbAb0eB";
    ContractGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

    long chainId = 1337;
    PollingTransactionReceiptProcessor transactionReceiptProcessor = new PollingTransactionReceiptProcessor(web3j, 1000, 60);
    TransactionManager txManager = new RawTransactionManager(web3j, credentials, chainId, transactionReceiptProcessor);


    /**
     * 添加商品
     *
     * @param name     商品名称
     * @param price    商品价格
     * @param quantity 商品数量
     * @return 添加商品的交易收据
     */
    public TransactionReceipt addSteelPlate(String name, BigInteger price, BigInteger quantity) throws Exception {
        // 加载智能合约实例
        SteelPlateContract contract = SteelPlateContract.load(CONTRACT_ADDRESS, web3j, txManager, gasProvider);
        return contract.addSteelPlate(name, price, quantity).send();
    }

    /**
     * 购买商品
     *
     * @param id    商品ID
     * @param value 支付的以太币数量
     * @return 购买商品的交易收据
     */
    public TransactionReceipt buySteelPlate(Integer id, BigInteger value, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider, long chainId) throws Exception {
        // 创建一个支持EIP-155的RawTransactionManager
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);

        // 用RawTransactionManager实例化合约
        SteelPlateContract contract = SteelPlateContract.load(CONTRACT_ADDRESS, web3j, transactionManager, gasProvider);

        return contract.buySteelPlate(BigInteger.valueOf(id), value).send();
    }


    /**
     * 获取商品信息
     *
     * @param id 商品ID
     * @return 购买商品的交易收据
     */
    public void getSteelPlate(BigInteger id) throws Exception {
        SteelPlateContract contract = SteelPlateContract.load(CONTRACT_ADDRESS, web3j, credentials, new DefaultGasProvider());
        Tuple6<BigInteger, String, BigInteger, BigInteger, String, String> result = contract.steelPlates(id).send();
        System.out.println("Steel Plate ID: " + result.component1().intValue());
        System.out.println("Steel Plate Name: " + result.component2());
        System.out.println("Steel Plate Price: " + result.component3().intValue());
        System.out.println("Steel Plate Quantity: " + result.component4().intValue());
        System.out.println("Steel Plate Added By: " + result.component5());
        System.out.println("Steel Plate Purchased By: " + result.component6());
    }
}