package com.example.systemmanage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@AllArgsConstructor // 생성자
@NoArgsConstructor  // 기본 생성자 추가 어노테이션
@ToString
@Entity
@Getter
public class Article extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "article_writer")
    private String article_writer;   // 작성자
    @Column(name = "article_title")
    private String article_title;
    @Column(name = "article_content")
    private String article_content;

    public void patch(Article article) {
        if(article.article_writer != null)
            this.article_writer = article.article_writer;
        if(article.article_title != null)
            this.article_title = article.article_title;
        if(article.article_content != null)
            this.article_content = article.article_content;
    }

//    @CreatedDate
//    private LocalDate article_createAt;
}
