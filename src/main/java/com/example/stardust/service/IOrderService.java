package com.example.stardust.service;

import com.example.stardust.entity.Order;
import org.springframework.http.ResponseEntity;
import org.web3j.protocol.Web3j;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:38
 */
public interface IOrderService {



    Order GETByOId(Integer oid);
    Integer updatePay(Integer oid);
}
