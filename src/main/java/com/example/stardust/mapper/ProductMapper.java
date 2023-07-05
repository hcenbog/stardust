package com.example.stardust.mapper;

import com.example.stardust.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/12 20:32
 */
public interface ProductMapper {
    /**
     * @return java.util.List<com.example.stardust.entity.Product>
     * @Description 展现商品
     * @create 2023/4/5 8:02
     **/
    List<Product> findHotList();

    /**
     * @return java.util.List<com.example.stardust.entity.Product>
     * @Description id 查询商品
     * @create 2023/4/5 8:02
     **/
    Product findById(Integer id);

    List<Product> searchKey(@Param("keyword") String keyword);
}
