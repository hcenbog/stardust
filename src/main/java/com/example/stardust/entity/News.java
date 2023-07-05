package com.example.stardust.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/12 17:43
 */
public class News extends BaseEntity implements Serializable {
    private Integer id;
    private String newsPath;
    private Date newsTime;
    private String newsName;
    private String newsUser;
    private String newsAbs;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", newsPath='" + newsPath + '\'' +
                ", newsTime=" + newsTime +
                ", newsName='" + newsName + '\'' +
                ", newsUser='" + newsUser + '\'' +
                ", newsAbs='" + newsAbs + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News news)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), news.getId()) && Objects.equals(getNewsPath(), news.getNewsPath()) && Objects.equals(getNewsTime(), news.getNewsTime()) && Objects.equals(getNewsName(), news.getNewsName()) && Objects.equals(getNewsUser(), news.getNewsUser()) && Objects.equals(getNewsAbs(), news.getNewsAbs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getNewsPath(), getNewsTime(), getNewsName(), getNewsUser(), getNewsAbs());
    }

    public String getNewsPath() {
        return newsPath;
    }

    public void setNewsPath(String newsPath) {
        this.newsPath = newsPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsUser() {
        return newsUser;
    }

    public void setNewsUser(String newsUser) {
        this.newsUser = newsUser;
    }

    public String getNewsAbs() {
        return newsAbs;
    }

    public void setNewsAbs(String newsAbs) {
        this.newsAbs = newsAbs;
    }

}
