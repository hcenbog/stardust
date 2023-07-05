package com.example.stardust.controller;

import com.example.stardust.Utile.JsonResult;
import com.example.stardust.controller.ex.*;
import com.example.stardust.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author AlHeae
 * @Description 控制层类的基类
 * @date 2022/11/9 13:51
 */
public class BaseController {
    /**
     * @Description 操作成功状态码
     * @create 2022/11/9 13:54
     **/
    public static final int OK = 200;

    //请求处理方法，这个方法的返回值，就是需要传递给全端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当项目产生异常，会被统一拦截到此方法中 这个方法充当的是请求处理方法，方法的返回值直接给到前端。
    @ExceptionHandler({ServiceException.class, FileUploadIOException.class}) //统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户数据不存在异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("用户密码验证错误异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("客户收货地址超出上限");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
        } else if (e instanceof CartNotFoundException) {
            result.setState(4007);
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新时产生位置异常");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }

        return result;
    }

    /**
     * 获取session对象的uid
     *
     * @return 当前用户id
     * @Description
     * @create 2023/3/6 20:11
     **/
    public final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的用户名
     *
     * @param session session对象
     * @return 当前用户id
     * @Description
     * @create 2023/3/6 20:11
     **/
    public final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();

    }

    /**
     * 获取当前登录用户的订单
     *
     * @param session session对象
     * @return 当前用户订单id
     * @Description
     * @create 2023/3/6 20:11
     **/
    public final Integer getIdFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("id").toString());
    }

    public final Integer getOidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("oid").toString());
    }
}
