package com.example.stardust.service;

import com.example.stardust.entity.News;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/12 17:57
 */
public interface INewsService {
    /**
     * 查询新闻
     *
     * @return 查询新闻
     */
    List<News> getAllNews();

    News getNewsById(Integer id);

    String readNewsContent(String newsPath);
}
