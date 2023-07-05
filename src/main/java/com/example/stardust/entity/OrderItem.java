package com.example.stardust.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:31
 */
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer pid;
    private String name;
    private String username;
    private String image;
    private Long price;
    private Integer num;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItem orderItem)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Objects.equals(getId(), orderItem.getId()) && Objects.equals(getPid(), orderItem.getPid()) && Objects.equals(getName(), orderItem.getName()) && Objects.equals(getUsername(), orderItem.getUsername()) && Objects.equals(getImage(), orderItem.getImage()) && Objects.equals(getPrice(), orderItem.getPrice()) && Objects.equals(getNum(), orderItem.getNum());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getPid(), getName(), getUsername(), getImage(), getPrice(), getNum());
    }

}
