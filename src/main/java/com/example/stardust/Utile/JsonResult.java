package com.example.stardust.Utile;

import java.io.Serializable;

/**
 * @author AlHeae
 * @Description Json格式的数据进行响应
 * @date 2022/11/8 21:06
 */
public class JsonResult<E> implements Serializable {
    /**
     * @Description 状态码
     */
    private Integer state;
    /**
     * @Description 描述信息
     */
    private String message;
    /**
     * @Description 数据类型
     */
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }


    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
