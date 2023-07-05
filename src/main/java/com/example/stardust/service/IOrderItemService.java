package com.example.stardust.service;

import com.example.stardust.entity.Order;
import com.example.stardust.entity.OrderItem;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/13 20:14
 */
public interface IOrderItemService {

    List<OrderItem> findByList(Integer id);

    Order create(Integer[] cids, Integer uid, String username);

    OrderItem getOrderById(Integer id);

}
