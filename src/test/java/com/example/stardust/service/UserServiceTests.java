package com.example.stardust.service;

import com.example.stardust.Trade.SteelPlateContract;
import com.example.stardust.entity.User;
import com.example.stardust.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author AlHeae
 * @Description 测试mapper包
 * @date 2022/11/6 22:36
 */
//表示标注当前类是个测试类，不会随同项目打包发送
@SpringBootTest
//表示启动单元测试类，不启动不会启动，需要传递一个参数，必须是springRunner实例类型
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    // 注册
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("HAE");
            user.setPassword("312");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            //获取类的对象，获取异常类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void update() throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException {
        String acc = "254233";
        userService.updateAddUser(2, acc);

    }

    //查询金额
    @Test
    public void changeAv() throws IOException {
        Integer uid = 15;
        String key = userService.findByUid(uid);
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        EthGetBalance balance = web3j.ethGetBalance(key, DefaultBlockParameterName.LATEST).send();
        BigInteger wei = balance.getBalance();
        System.out.println(wei);
    }

    //可以交易
    @Test
    public void finsa() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        Credentials credentials = WalletUtils.loadCredentials("254233", "D:\\zhuomian\\sol\\mde\\ethereum2\\keystore\\UTC--2023-04-29T06-48-33.277017400Z--cc09b796f245746e07bf4c1b6b59f4e4a02d3ff6.json");
        Integer uid = 15;
        String key = userService.findByUid(uid);
        TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3j, credentials, key,
                BigDecimal.valueOf(1.0), Convert.Unit.ETHER).send();
    }

    //添加
    @Test
    public void fdinsa() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        String CONTRACT_ADDRESS = "0xb97174441e85Bd3f9E6168DD832A4152FADc0757";
        Credentials credentials = Credentials.create("4b2131582338d8e33b27157002835d99b5302e309ee41b621f55ce87ecbe0449");
        ContractGasProvider gasProvider = new StaticGasProvider(DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        // 在这里添加链ID，这将启用EIP-155重放保护
        long chainId = 1337;
        PollingTransactionReceiptProcessor transactionReceiptProcessor = new PollingTransactionReceiptProcessor(web3j, 1000, 60);
        TransactionManager txManager = new RawTransactionManager(web3j, credentials, chainId, transactionReceiptProcessor);


        // 加载智能合约实例
        SteelPlateContract contract = SteelPlateContract.load(CONTRACT_ADDRESS, web3j, txManager, gasProvider);

        // 创建钢板并添加到智能合约中
        String name = "MySteelPlate";
        BigInteger price = BigInteger.valueOf(100);
        BigInteger quantity = BigInteger.valueOf(10);
        TransactionReceipt txReceipt = contract.addSteelPlate(name, price, quantity).send();
    }


    //获取密钥
    @Test
    public void fdf() throws CipherException, IOException {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        // 账户地址
        String address = "0x9fc418e01c7643037cc043981c223ea1c3e186f5";
        Credentials credentials = WalletUtils.loadCredentials("254233", "D:\\zhuomian\\stardust\\mde\\ethereum\\keystore\\UTC--2023-05-02T02-40-39.829161700Z--9fc418e01c7643037cc043981c223ea1c3e186f5.json");
        // 打印私钥
        System.out.println(credentials.getEcKeyPair().getPrivateKey().toString(16));
    }


    //登录账号
    @Test

    public void login() {
        User user = userService.login("HAE", "312");
        System.out.println(user);
    }

    @Test
    public void findBykey() {
        Integer uid = 1;
        System.out.println(userService.findByUid(uid));
    }

    //修改密码
    @Test
    public void changePassword() {
        userService.changePassword(15, "232", "312", "123");
    }


    @Test
    public void getByUid() {
        System.err.println(userService.getByUid(15));
    }

    @Test
    public void changeInfo() {
        User user = new User();
        user.setPhone("1111111");
        user.setEmail("234234@a.com");
        userService.changeInfo(15, "管理员", user);
    }

    @Test
    public void changeAva() {
        userService.changeAva(15, "/upload/ava2.png", "管理员");
    }
}
