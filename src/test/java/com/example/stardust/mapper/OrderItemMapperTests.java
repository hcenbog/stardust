package com.example.stardust.mapper;

import com.example.stardust.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/27 21:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemMapperTests {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setPid(21);
        orderItem.setUsername("小红");
        orderItem.setName("高档铅笔");
        Integer rows = orderItemMapper.insertOrderItem(orderItem);
        System.out.println("rows=" + rows);
    }
}
