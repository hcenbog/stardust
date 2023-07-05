package com.example.stardust.controller;

import com.example.stardust.Utile.JsonResult;
import com.example.stardust.entity.Order;
import com.example.stardust.entity.OrderItem;
import com.example.stardust.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 20:20
 */
@RestController
@RequestMapping("orderItems")
public class OrderItemController extends BaseController {
    @Autowired
    private IOrderItemService orderItemService;

    @RequestMapping("InsertOrIt")
    public JsonResult<Order> create(Integer[] cids, HttpSession session) {
        // 从Session中取出uid和usernames
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        Order data = orderItemService.create(cids, uid, username);
        //向session对象完成数据的绑定
        session.setAttribute("oid", data.getOid());
        System.out.println("OID set in session: " + data.getOid());
        return new JsonResult<Order>(OK, data);
    }

    @RequestMapping("List")
    public JsonResult<List<OrderItem>> getBuyList(HttpSession session) {
        Integer id = getIdFromSession(session);
        List<OrderItem> data = orderItemService.findByList(id);
        return new JsonResult<List<OrderItem>>(OK, data);
    }
}