package com.example.stardust.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/13 4:05
 */
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartVO cartVO)) {
            return false;
        }
        return Objects.equals(getCid(), cartVO.getCid()) && Objects.equals(getUid(), cartVO.getUid()) && Objects.equals(getPid(), cartVO.getPid()) && Objects.equals(getPrice(), cartVO.getPrice()) && Objects.equals(getNum(), cartVO.getNum()) && Objects.equals(name, cartVO.name)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCid(), getUid(), getPid(), getPrice(), getNum(), name);
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    private Long realPrice;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

