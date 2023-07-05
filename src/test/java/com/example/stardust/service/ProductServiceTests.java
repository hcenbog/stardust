package com.example.stardust.service;

import com.example.stardust.entity.Product;
import com.example.stardust.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/13 3:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {
    @Autowired
    private IProductService productService;

    @Test
    public void findById() {
        try {
            Integer id = 1;
            Product result = productService.findById(id);
            System.out.println(result);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void search(){
        String key = "板";
        List<Product> products = productService.search(key);
        System.out.println(products);
    }
}
