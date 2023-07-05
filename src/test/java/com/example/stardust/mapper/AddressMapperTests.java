package com.example.stardust.mapper;

import com.example.stardust.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AlHeae
 * @Description 测试mapper包
 * @date 2023/4/6 22:36
 */
//表示标注当前类是个测试类，不会随同项目打包发送
@SpringBootTest
//表示启动单元测试类，不启动不会启动，需要传递一个参数，必须是springRunner实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(15);
        address.setPhone("12121212");
        address.setName("女朋友");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(15);
        System.out.println(count);
    }
}
