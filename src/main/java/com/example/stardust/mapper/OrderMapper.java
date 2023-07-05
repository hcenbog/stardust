package com.example.stardust.mapper;

import com.example.stardust.entity.Order;

import java.util.Map;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:33
 */
public interface OrderMapper {
    /**
     * 插入订单数据
     *
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * @return com.example.stardust.entity.Order
     * @Description 根据oid查询订单
     * @create 2023/4/28 11:04
     **/
    Order findByOid(Integer oid);

    Integer updatePay(Order order);
}
