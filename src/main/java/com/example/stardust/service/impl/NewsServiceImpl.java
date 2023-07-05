package com.example.stardust.service.impl;

import com.example.stardust.entity.News;
import com.example.stardust.mapper.NewsMapper;
import com.example.stardust.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/12 17:56
 */
@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getAllNews() {
        return newsMapper.getAllNews();
    }

    @Override
    public News getNewsById(Integer id) {
        return newsMapper.getNewsById(id);
    }

    public String readNewsContent(String newsPath) {
        try {
            File file = new File(getClass().getClassLoader().getResource("news/" + newsPath).getFile());
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}




