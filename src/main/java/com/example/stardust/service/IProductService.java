package com.example.stardust.service;

import com.example.stardust.entity.Product;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/12 20:54
 */
public interface IProductService {
    List<Product> findHotList();

    Product findById(Integer id);

    List<Product> search(String keyword);
}
