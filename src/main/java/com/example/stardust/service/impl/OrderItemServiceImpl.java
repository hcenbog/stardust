package com.example.stardust.service.impl;

import com.example.stardust.entity.Order;
import com.example.stardust.entity.OrderItem;
import com.example.stardust.mapper.OrderItemMapper;
import com.example.stardust.mapper.OrderMapper;
import com.example.stardust.service.ICartService;
import com.example.stardust.service.IOrderItemService;
import com.example.stardust.service.IUserService;
import com.example.stardust.service.ex.InsertException;
import com.example.stardust.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 20:15
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IUserService userService;

    @Override
    public List<OrderItem> findByList(Integer id) {
        List<OrderItem> list = orderItemMapper.findByList(id);
        for (OrderItem orderitem : list) {
            orderitem.setCreatedUser(null);
            orderitem.setCreatedTime(null);
            orderitem.setModifiedUser(null);
            orderitem.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Order create(Integer[] cids, Integer uid, String username) {
        Date date = new Date();
        List<CartVO> carts = cartService.getVOByCids(uid, cids);
        // 计算这些商品的总价
        long totalPrice = 0;
        long num = 0;
        for (CartVO cart : carts) {
            totalPrice += cart.getPrice() * cart.getNum();
            num += cart.getNum();
        }
        //创建订单id
        String hX = generateOrderNumber(date, totalPrice, "XCG", username);
        OrderItem item = new OrderItem();
        for (CartVO cart : carts) {
            item.setUsername(username);
            item.setPid(cart.getPid());
            item.setName(cart.getName());
            item.setPrice(cart.getPrice());
            item.setNum(cart.getNum());
// 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(date);
            item.setModifiedUser(username);
            item.setModifiedTime(date);
            Integer row1 = orderItemMapper.insertOrderItem(item);
            if (row1 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }

        }
        Order order = new Order();
        order.setUid(uid);
        order.setOrderId(hX);
        order.setBuyer(username);
        order.setSeller("XCG");
        order.setTotalPrice(BigInteger.valueOf(totalPrice));
        order.setNum(BigInteger.valueOf(num));
        order.setStatus(0);
        order.setOrderTime(date);
        order.setPayTime(null);
        //        四项数据
        order.setCreatedUser(username);
        order.setCreatedTime(date);
        order.setModifiedUser(username);
        order.setModifiedTime(date);
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }
        return order;
    }

    //加密生成订单号
    public static String generateOrderNumber(Date date, double price, String XCG, String username) {
        // 生成时间戳
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        // 生成随机数
        Random random = new Random();
        String randomNumStr = "";
        for (int i = 0; i < 10; i++) {
            int randomNum = random.nextInt(36);
            if (randomNum < 10) {
                randomNumStr += String.valueOf(randomNum);
            } else {
                randomNumStr += (char) ('a' + randomNum - 10);
            }
        }
        // 组合订单信息
        String orderInfo = price + XCG + username;
        // 对订单信息进行哈希
        String orderNumber = sha256(timeStamp + randomNumStr + orderInfo);
        return orderNumber;
    }


    /**
     * @param str 哈希值
     * @return java.lang.String
     * @Description 哈希算法加密
     * @create 2023/4/27 22:23
     **/
    private static String sha256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderItem getOrderById(Integer id) {
        OrderItem orderItem = orderItemMapper.getOrderById(id);
        if (orderItem == null) {
            throw new InsertException("该订单不存在");
        }
        return orderItemMapper.getOrderById(id);
    }

/*
  解密算法
 public static OrderInfo parseOrderNumber(String orderNumber) {
 // 解析订单号
 String orderInfo = sha256(orderNumber).substring(16);
 String name = orderInfo.substring(0, orderInfo.length() - 1 - String.valueOf(XCG).length() - String.valueOf(price).length() - String.valueOf(num).length() - String.valueOf(username).length());
 double price = Double.parseDouble(orderInfo.substring(orderInfo.length() - 1 - String.valueOf(XCG).length() - String.valueOf(num).length() - String.valueOf(price).length() - String.valueOf(username).length(), orderInfo.length() - 1 - String.valueOf(XCG).length() - String.valueOf(num).length() - String.valueOf(username).length()));
 int num = Integer.parseInt(orderInfo.substring(orderInfo.length() - 1 - String.valueOf(XCG).length() - String.valueOf(num).length() - String.valueOf(username).length(), orderInfo.length() - 1 - String.valueOf(XCG).length() - String.valueOf(username).length()));
 String XCG = orderInfo.substring(orderInfo.length() - String.valueOf(XCG).length() - String.valueOf(username).length(), orderInfo.length() - String.valueOf(username).length());
 String username = orderInfo.substring(orderInfo.length() - String.valueOf(username).length());
 // 解析时间戳和随机数
 String timeStamp = sha256(orderNumber).substring(0, 14);
 String randomNumStr = sha256(orderNumber).substring(14, 24);
 // 封装订单信息
 OrderInfo orderInfo = new OrderInfo();
 orderInfo.setTimeStamp(timeStamp);
 orderInfo.setName(name);
 orderInfo.setPrice(price);
 orderInfo.setNum(num);
 orderInfo.setXCG(XCG);
 orderInfo.setUsername(username);
 return orderInfo;
 }
 */
}