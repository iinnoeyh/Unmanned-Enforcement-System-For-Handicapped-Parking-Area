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
public class Notice extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "notice_writer")
    private String notice_writer;
    @Column(name = "notice_title")
    private String notice_title;
    @Column(name = "notice_content")
    private String notice_content;

    public void patch(Notice notice) {
        if(notice.notice_writer != null)
            this.notice_writer = notice.notice_writer;
        if(notice.notice_title != null)
            this.notice_title = notice.notice_title;
        if(notice.notice_content != null)
            this.notice_content = notice.notice_content;
    }
}
