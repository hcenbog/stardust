package com.example.stardust.mapper;

import com.example.stardust.entity.Cart;
import com.example.stardust.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(15);
        cart.setPid(1);
        cart.setNum(1);
        cart.setPrice(3L);
        Integer rows = cartMapper.insert(cart);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(2, 1, "管理员", new Date());
    }

    @Test
    public void findVOByUid() {
        List<CartVO> list = cartMapper.findVOByUid(27);
        System.out.println(list);
    }
}
