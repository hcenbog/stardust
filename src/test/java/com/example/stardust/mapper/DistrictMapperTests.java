package com.example.stardust.mapper;

import com.example.stardust.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/11 23:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> list = districtMapper.findByParent("210100");
        for (District d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode() {
        String code = "540000";
        String name = districtMapper.findNameByCode(code);
        System.out.println(name);
    }
}
