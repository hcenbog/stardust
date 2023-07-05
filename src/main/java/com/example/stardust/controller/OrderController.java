package com.example.stardust.controller;

import com.example.stardust.entity.Order;
import com.example.stardust.service.IOrderService;
import com.example.stardust.service.impl.SteelPlateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:42
 */
@RestController
@ResponseBody
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private SteelPlateServiceImpl steelPlateService;

    /**
     * @param session 获取商品订单信息
     * @return java.lang.String
     * @Description 添加商品
     * @create 2023/5/4 10:04
     **/
    @GetMapping("/addSteel")
    public String addSteelPlate(HttpSession session) {
        Integer oid = getOidFromSession(session);
        Order data = orderService.GETByOId(oid);
        String name = String.valueOf(data.getOid());
        BigInteger price = data.getTotalPrice();
        BigInteger quantity = data.getNum();
        try {
            TransactionReceipt receipt = steelPlateService.addSteelPlate(name, price, quantity);
            return "您的购买请求已经上传区块链";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传区块链失败，请稍后重试";
        }
    }

    @GetMapping("/Price")
    public ResponseEntity<Map<String, Object>> getOrderData(HttpSession session) {
        Integer oid = getOidFromSession(session);
        Order data = orderService.GETByOId(oid);

        BigInteger totalPriceCNY = data.getTotalPrice();
        BigDecimal usdCnyExchangeRate = new BigDecimal("7"); // 预设汇率：1美元 = 6.925人民币
        BigDecimal cnyToUsdRate = BigDecimal.ONE.divide(usdCnyExchangeRate, 10, RoundingMode.HALF_UP);
        BigDecimal totalPriceUsd = new BigDecimal(totalPriceCNY).multiply(cnyToUsdRate);
        BigInteger totalPriceGas = totalPriceUsd.divide(BigDecimal.ONE, 0, RoundingMode.HALF_UP).toBigInteger();

        // 将数据放入Map中并返回给前端
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("oid", oid);
        responseData.put("totalPriceCNY", totalPriceCNY);
        responseData.put("totalPriceGas", totalPriceGas);
        return ResponseEntity.ok(responseData);
    }

    //买
    @PostMapping("/buy")
    public ResponseEntity<String> submitTransaction(@RequestParam("privateKey") String privateKey, @RequestParam("cnyValue") BigInteger cnyValue, HttpSession session) {
        try {
            Integer oid = getOidFromSession(session);
            // 根据提供的私钥创建凭证
            Credentials credentials = Credentials.create(privateKey);
            // 创建Web3j实例以与以太坊网络交互
            Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
            // 直接使用人民币金额调用buySteelPlate方法
            steelPlateService.buySteelPlate(oid, cnyValue, web3j, credentials, new DefaultGasProvider(), 1337);
            // 如果交易成功，则更新订单支付状态
            orderService.updatePay(oid);
            return ResponseEntity.ok("交易成功，订单支付状态已更新。");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("交易失败，原因：" + e.getMessage());
        }
    }

}
