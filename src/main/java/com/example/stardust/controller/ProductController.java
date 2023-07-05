package com.example.stardust.controller;

import com.example.stardust.Utile.JsonResult;
import com.example.stardust.entity.Product;
import com.example.stardust.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/12 21:12
 */
@RestController
@ResponseBody
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        // 调用业务对象执行获取数据
        Product data = productService.findById(id);
        // 返回成功和数据
        return new JsonResult<Product>(OK, data);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam("keyword") String keyword) {
        List<Product> products = productService.search(keyword);
        return products;
    }
}
