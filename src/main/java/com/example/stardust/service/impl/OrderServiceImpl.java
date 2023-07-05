package com.example.stardust.service.impl;

import com.example.stardust.entity.Order;
import com.example.stardust.mapper.OrderMapper;
import com.example.stardust.service.IOrderItemService;
import com.example.stardust.service.IOrderService;
import com.example.stardust.service.IUserService;
import com.example.stardust.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:39
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private IUserService userService;

    @Override
    public Order GETByOId(Integer oid) {
        Order order = orderMapper.findByOid(oid);
        if (order == null) {
            throw new InsertException("未找到订单");
        }
        order.setStatus(null);
        order.setPayTime(null);
        order.setModifiedUser(null);
        order.setModifiedTime(null);
        order.setCreatedTime(null);
        order.setCreatedUser(null);
        return orderMapper.findByOid(oid);
    }
    @Override
    public Integer updatePay(Integer oid) {
        Order order = new Order();
        order.setOid(oid);
        order.setStatus(1);
        order.setPayTime(new Date());
        return orderMapper.updatePay(order);
    }
}

