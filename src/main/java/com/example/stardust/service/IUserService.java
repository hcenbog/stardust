package com.example.stardust.service;

import com.example.stardust.entity.User;
import com.example.stardust.service.ex.UpdateException;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author AlHeae
 * @Description 用户模块业务接口
 * @date 2022/11/7 17:10
 */
public interface IUserService {
    /**
     * @param user 用户的数据对象
     * @return void
     * @Description 用户注册方法
     * @create 2022/11/7 17:11
     **/
    void reg(User user);

    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 用户密码
     * @return null
     * @Description 用户登录方法
     * @create 2023/3/5 21:08
     **/
    User login(String username, String password);

    /**
     * 修改密码
     *
     * @param uid         当前登录的用户id
     * @param username    用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @create 2023/3/5 21:08
     **/
    void changePassword(Integer uid, String username, String oldPassword, String newPassword) throws UpdateException;

    void updateAddUser(Integer uid, String password) throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException;

    /**
     * 获取当前登录的用户的信息
     *
     * @param uid 当前登录的用户的id
     * @return 当前登录的用户的信息
     */

    User getByUid(Integer uid);

//    User findByName(String username);

    String findByUid(Integer uid);

    /**
     * 修改用户资料
     *
     * @param uid      当前登录的用户的id
     * @param username 当前登录的用户名
     * @param user     用户的新的数据
     */

    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户头像
     *
     * @param uid      当前登录的用户的id
     * @param avatar   用户的头像
     * @param username 当前登录的用户名
     */
    void changeAva(Integer uid, String avatar, String username);
}
