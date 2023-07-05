package com.example.stardust.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    //    @Test
//    public void insertOrder() {
//        Order order = new Order();
//        order.setUid(15);
//        order.setRecvName("小王");
//        Integer rows = orderMapper.insertOrder(order);
//        System.out.println("rows=" + rows);
//    }
    @Test
    public void oid() {
        Integer OID = 10;
        System.out.println(orderMapper.findByOid(OID));
    }
}