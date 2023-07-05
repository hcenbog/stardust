package com.example.stardust.service.impl;

import com.example.stardust.entity.Cart;
import com.example.stardust.entity.Product;
import com.example.stardust.mapper.CartMapper;
import com.example.stardust.mapper.ProductMapper;
import com.example.stardust.service.ICartService;
import com.example.stardust.service.ex.*;
import com.example.stardust.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author AlHeae
 * @Description 用户模块实现类 //添加注解，将当前对象交给spring进行管理。自动创建对象以及对象的维护
 * @date 2022/11/7 17:13
 */
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if (result == null) {
            // 是：表示该用户并未将该商品添加到购物车
            // 创建Cart对象
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
// 封装数据：4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入商品数据时出现未知错误，请联系系统管理员");
            }
        } else {
            Integer cid = result.getCid();
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(cid, num, username, date);
            if (rows != 1) {
                throw new UpdateException("更新数据产生未知异常");
            }
        }
    }

    @Override
    public void deleteByCid(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = cartMapper.deleteByCid(cid);
        if (rows != 1) {
            throw new DeleteException("删除商品数据时出现未知错误，请联系系统管理员");
        }
        cartMapper.deleteByCid(cid);
    }


    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        Integer num = result.getNum() + 1;
        Date now = new Date();
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);
        list.removeIf(cart -> !cart.getUid().equals(uid));
        return list;
    }

}