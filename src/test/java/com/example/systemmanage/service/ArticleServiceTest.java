package com.example.systemmanage.service;

import com.example.systemmanage.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @org.junit.jupiter.api.Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "title_test1", "content_test1");
        Article b = new Article(2L, "title_test2", "content_test2");
        Article c = new Article(3L, "title_test3", "content_test3");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }
}