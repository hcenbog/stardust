package com.example.stardust.service;

import com.example.stardust.service.ex.ServiceException;
import com.example.stardust.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 3:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart() {
        try {
            Integer uid = 16;
            Integer pid = 2;
            Integer num = 1;
            String username = "Tom";
            cartService.addToCart(uid, pid, num, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getVOByUid() {
        List<CartVO> list = cartService.getVOByUid(27);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void deleteByCid() throws Exception {
        try {
            Integer cid = 3;
            Integer uid = 15;
            String username = "小红";
            cartService.deleteByCid(cid, uid, username);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }
}