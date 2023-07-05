package com.example.stardust.mapper;

import com.example.stardust.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author AlHeae
 * @Description 测试mapper包
 * @date 2022/11/6 22:36
 */
//表示标注当前类是个测试类，不会随同项目打包发送
@SpringBootTest
//表示启动单元测试类，不启动不会启动，需要传递一个参数，必须是springRunner实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    //创建
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("HER");
        user.setPassword("333");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    //查询
    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

    @Test
    public void findByKey() {
        String a = userMapper.findByKey(2);
        System.out.println(a);
    }

    @Test
    public void update() {
        userMapper.updateAddUser(3, "0xd1deaa775908ba5e1f55ebd4cb764909f899dca1");
    }

    //修改
    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(16, "321", "管理员", new Date());
    }

    //查询
    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(15));
    }

    //修改
    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(15);
        user.setPhone("11111111");
        user.setEmail("234234@.com");
        userMapper.updateInfoByUid(user);
    }

    //更新头像方法
    @Test
    public void updateAvatarByUid() {
        Integer uid = 15;
        String avatar = "/upload/avatar.png";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvaByUid(uid, avatar, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }


}
