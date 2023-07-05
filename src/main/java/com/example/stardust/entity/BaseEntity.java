package com.example.stardust.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author AlHeae
 * @Description 实体类的基类
 * @date 2022/11/4 16:30
 */
//@Data

public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return getCreatedUser().equals(that.getCreatedUser()) && getCreatedTime().equals(that.getCreatedTime()) && getModifiedUser().equals(that.getModifiedUser()) && getModifiedTime().equals(that.getModifiedTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedUser(), getCreatedTime(), getModifiedUser(), getModifiedTime());
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdUser='" + createdUser + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
