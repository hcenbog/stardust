package com.example.stardust.service.impl;

import com.example.stardust.entity.Product;
import com.example.stardust.mapper.ProductMapper;
import com.example.stardust.service.IProductService;
import com.example.stardust.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/12 20:57
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setCatId(null);
            product.setQuantity(null);
            product.setWeight(null);
            product.setPrice(null);
            product.setStock(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
// 判断查询结果是否为null
        if (product == null) {
// 是：抛出ProductNotFoundException
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        return product;
    }

    @Override
    public List<Product> search(String keyword) {
        return productMapper.searchKey(keyword);
    }
}
