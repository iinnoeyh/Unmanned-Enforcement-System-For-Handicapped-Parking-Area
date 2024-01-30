package com.example.systemmanage.service;

import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoticeServiceTest {
    @Autowired
    NoticeService noticeService;
    @Test
    void index() {
        // 1. 예상 데이터
        Notice a = new Notice(1L, "title_test1", "content_test1");
        Notice b = new Notice(2L, "title_test2", "content_test2");
        Notice c = new Notice(3L, "title_test3", "content_test3");
        List<Notice> expected = new ArrayList<Notice>(Arrays.asList(a, b, c));
        // 2. 실제 데이터
        List<Notice> notices = noticeService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), notices.toString());
    }
}