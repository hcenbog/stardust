package com.example.stardust.controller;

import com.example.stardust.entity.News;
import com.example.stardust.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AlHeae
 * @Description
 * @date 2023/3/12 18:01
 */
@RestController
@ResponseBody
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> newsDataList = new ArrayList<>();

        for (News news : newsList) {
            Map<String, Object> newsData = new HashMap<>();
            newsData.put("id", news.getId());
            newsData.put("newsPath", news.getNewsPath());
            newsData.put("newsTime", sdf.format(news.getNewsTime()));
            newsData.put("newsName", news.getNewsName());
            newsData.put("newsUser", news.getNewsUser());
            newsData.put("newsAbs", news.getNewsAbs());
            newsDataList.add(newsData);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("newsList", newsDataList);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> newsDetail(@PathVariable("id") Integer id) {
        News news = newsService.getNewsById(id);
        Map<String, Object> response = new HashMap<>();
        if (news != null) {
            String content = newsService.readNewsContent(news.getNewsPath());
            response.put("news", news);
            response.put("content", content);
        }
        return response;
    }
}
