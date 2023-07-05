package com.example.stardust.service;

import com.example.stardust.vo.CartVO;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/13 2:37
 */
public interface ICartService {
    /**
     * 将商品添加到购物车
     *
     * @param uid      当前登录用户的id
     * @param pid      商品的id
     * @param amount   增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    void deleteByCid(Integer cid, Integer uid, String username);

    List<CartVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCids(Integer uid, Integer[] cids);

}