package com.example.stardust.mapper;

import com.example.stardust.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author AlHeae
 * @Description 用户模块的持久层接口
 * @date 2022/11/5 14:02
 */
@Mapper
public interface UserMapper {
    /**
     * @param user 用户数据
     * @return 受影响的行数(增 · 删 · 改 · 查 。 根据返回值判断是否执行成功)
     * @Description 插入用户数据
     * @create 2022/11/5 15:15
     **/
    Integer insert(User user);

    /**
     * @param username 用户名
     * @return 如果找到对应用户，返回用户数据。如果无，返回NULL
     * @Description 根据用户名查询用户数据
     * @create 2022/11/5 15:20
     **/
    User findByUsername(String username);

    String findByKey(Integer uid);

    User findByName(String username);

    /**
     * 根据用户id修改密码
     *
     * @param uid          用户id
     * @param password     新密码
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改时间
     * @return 返回值受影响的行数
     * @Description
     * @create 2023/3/7 14:14
     **/
    Integer updatePasswordByUid(@Param("uid") Integer uid, @Param("password") String password, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    Integer updateAddUser(@Param("uid") Integer uid, @Param("accountAddress") String accountAddress);

    User findByUid(Integer uid);

    Integer updateInfoByUid(User user);

    /**
     * 修改头像
     *
     * @Param uid
     * @Param ava
     * @Param modifiedUser
     * @Param modifiedTime
     * @Description
     * @create 2023/3/9 14:45
     **/
    Integer updateAvaByUid(@Param("uid") Integer uid,
                           @Param("avatar") String avatar,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);
}
