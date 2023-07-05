package com.example.stardust.controller;

import com.example.stardust.Utile.JsonResult;
import com.example.stardust.controller.ex.*;
import com.example.stardust.entity.User;
import com.example.stardust.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.toUpperCase;

/**
 * @author AlHeae
 * @Description 继承了BaseController @Controller+@ResponseBody
 * @date 2022/11/8 21:20
 */
@RestController()
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }

    @PostMapping("/register")
    public void register(HttpServletResponse response) {
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            String privateKey = ecKeyPair.getPrivateKey().toString(16);

            Credentials credentials = Credentials.create(privateKey);
            String address = credentials.getAddress();

            System.out.println("Address: " + address);

            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=private_key.txt");
            response.getOutputStream().write(privateKey.getBytes());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        //向session对象完成数据的绑定
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
// 从HttpSession对象中获取uid
        Integer uid = getuidFromSession(session);
// 调用业务对象执行获取数据
        User data = userService.getByUid(uid);
// 响应成功和数据
        return new JsonResult<User>(OK, data);
    }

    @GetMapping("/ByKey")
    public JsonResult<String> findByKey(HttpSession session) {
        Integer uid = getuidFromSession(session);
        String account = userService.findByUid(uid);
        return new JsonResult<String>(OK, account);
    }

    @GetMapping("wei")
    public JsonResult<String> findByWeilters(HttpSession session) throws IOException {
        Integer uid = getuidFromSession(session);
        String key = userService.findByUid(uid);
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        EthGetBalance balance = web3j.ethGetBalance(key, DefaultBlockParameterName.LATEST).send();
        BigInteger wei = balance.getBalance();
        return new JsonResult<String>(OK, wei.toString());
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<Void>(OK);
    }

    //上传数据
    /**
     * 头像文件大小的上限值(10MB)
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /**
     * 允许上传的头像的文件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    /**
     * @param session
     * @param file
     * @return com.example.stardust.Utile.JsonResult<java.lang.String>
     * @Description
     * @create 2023/4/9 15:17
     **/
    @ResponseBody
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }
        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) {
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：" + AVATAR_TYPES);
        }
        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 保存的头像文件的文件名

        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename" + originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString() + toUpperCase() + suffix;
        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重新尝试");
        }
        // 头像路径
        String ava = "/upload/" + filename;
        // 从Session中获取uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        userService.changeAva(uid, ava, username);
        // 返回成功头像路径
        return new JsonResult<String>(OK, ava);
    }
}

