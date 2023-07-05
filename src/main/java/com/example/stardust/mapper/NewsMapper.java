package com.example.stardust.mapper;

import com.example.stardust.entity.News;

import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/12 17:42
 */
public interface NewsMapper {
    List<News> getAllNews();

    News getNewsById(Integer id);
}
