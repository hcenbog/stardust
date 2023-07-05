package com.example.stardust.mapper;

import com.example.stardust.entity.OrderItem;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/27 20:32
 */
public interface OrderItemMapper {
    /**
     * 插入订单商品数据
     *
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    List<OrderItem> findByList(Integer id);

    OrderItem getOrderById(Integer id);
}
