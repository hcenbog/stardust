package com.example.stardust.service.impl;

import com.example.stardust.entity.User;
import com.example.stardust.mapper.UserMapper;
import com.example.stardust.service.IUserService;
import com.example.stardust.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.UUID;

/**
 * @author AlHeae
 * @Description 用户模块实现类 //添加注解，将当前对象交给spring进行管理。自动创建对象以及对象的维护
 * @date 2022/11/7 17:13
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername(username)判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        //判断集是否为空 则抛出用户名被占用
        if (result != null) {
            //抛出异常
            throw new UsernameDuplicateException("用户名被占用");
        }

        //user交给insert之前进行加密处理:
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().replace("-", "");
        String md5password = getencode(oldPassword, salt);
        user.setPassword(md5password);
        user.setSalt(salt);

        //一系列数据补全操作，is_delete设置为0
        user.setAvater(null);
        user.setIsDelete(0);
        user.setCreatedUser(user.getCreatedUser());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //没有被占用则执行注册功能
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }

    }

    @Override
    public User login(String username, String password) {
        // 根据用户名查询用户数据是否存在 不存在抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户名不存在");
        }
        // 根据用户密码是否匹配数据库 不符合抛出异常
        // 先获取加密密码 和用户密码进行比较
        String oldPassword = result.getPassword();
        // 先获得盐值 盐值来自上次注册获得的
        String salt = result.getSalt();
        // 将用户的密码按照md5算法的规则进行加密
        String md5password = getencode(password, salt);
        if (!md5password.equals(oldPassword)) {
            throw new PasswordNotMatchException("密码错误");
        }

        // 判断is_delete字段是否为1 判断是否为删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户已被删除");
        }
        // 调用mapper层findByUsername
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());

        // 将当前用户数据返回 返回数据给其他页面使用
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) throws UpdateException {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete().equals(1)) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        //原密码与新密码进行比较
        String salt = result.getSalt();
        String oldEnCode = getencode(oldPassword, salt);
        if (!result.getPassword().contentEquals(oldEnCode)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("原密码错误");
        }
        // 创建当前时间对象
        Date now = new Date();
        String newEnCode = getencode(newPassword, salt);
        Integer rows = userMapper.updatePasswordByUid(uid, newEnCode, username, now);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误");
        }
    }

    //创建以太坊账户
    @Override
    public void updateAddUser(Integer uid, String password) throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException {
        // 以太坊节点的UR
        String ETHEREUM_NODE_URL = "http://127.0.0.1:8545";
        // 以太坊节点的URL
        Web3j web3j = Web3j.build(new HttpService(ETHEREUM_NODE_URL));
        //        钱包密码
        String WALLET_PASSWORD = password;
//        钱包地址
        String WALLET_DIRECTORY = "D:\\zhuomian\\sol\\mde\\ethereum\\keystore";
        // 创建一个新的以太坊账户
        String walletFileName = WalletUtils.generateNewWalletFile(WALLET_PASSWORD, new File(WALLET_DIRECTORY));
        Credentials credentials = WalletUtils.loadCredentials(WALLET_PASSWORD, new File(WALLET_DIRECTORY + "/" + walletFileName));
        String accoAdd = credentials.getAddress();
        Integer rows = userMapper.updateAddUser(uid,  walletFileName);
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
// 判断查询结果中的isDelete是否为1
        if (result.getIsDelete().equals(1)) {
// 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        return user;
    }

//    @Override
//    public User findByName(String username) {
//        return findByName(username);
//    }

    @Override
    public String findByUid(Integer uid) {
        return userMapper.findByKey(uid);
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据为空或已删除");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误");
        }
    }

    @Override
    public void changeAva(Integer uid, String avatar, String username) {
        //查询数据是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据为空或已删除");
        }
        Integer rows = userMapper.updateAvaByUid(uid, avatar, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误");
        }


    }

    //user交给insert之前进行加密处理:
    public String getencode(String oldPassword, String salt) {
        // 加密过程
        // 1. 使用MD5算法
        // 2. 使用随机的盐值
        // 3. 循环5次
        // 4. 盐的处理方式为：盐 + 原密码 + 盐 + 原密码 + 盐
        // 注意：因为使用了随机盐，盐值必须被记录下来，本次的返回结果使用$分隔盐与密文
        String encodedPassword = oldPassword;
        for (int i = 0; i < 5; i++) {
            encodedPassword = DigestUtils.md5DigestAsHex(
                    (salt + encodedPassword + salt + encodedPassword + salt).getBytes());
        }
        return encodedPassword;
    }
}
