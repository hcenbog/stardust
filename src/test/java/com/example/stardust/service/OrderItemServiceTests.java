package com.example.stardust.service;

import com.example.stardust.entity.OrderItem;
import com.example.stardust.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/27 21:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemServiceTests {
    @Autowired
    private IOrderItemService orderItemService;

//    @Test
//    public void create() {
//        try {
//            Integer[] cids = {1};
//            Integer uid = 15;
//            String username = "alheae";
//            OrderItem orderItem = orderItemService.create(cids, uid, username);
//            System.out.println("oderItem");
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
//    }
}
