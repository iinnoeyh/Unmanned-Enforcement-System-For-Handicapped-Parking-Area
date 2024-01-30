package com.example.systemmanage.dto;

import com.example.systemmanage.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@AllArgsConstructor // 생성자
@ToString
public class ArticleDto {
    private Long id;
    private String article_writer;   // 작성자
    private String article_title;   // 제목
    private String article_content; // 내용

    public Article toEntity() {
        return new Article(id, article_writer, article_title, article_content);
    }
}
