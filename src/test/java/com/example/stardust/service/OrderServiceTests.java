package com.example.stardust.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Test
    public void sd() {
        Integer od = 10;
       System.out.println(orderService.GETByOId(od));
    }
}
