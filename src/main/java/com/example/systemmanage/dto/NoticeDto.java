package com.example.systemmanage.dto;

import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor // 생성자
@ToString
public class NoticeDto {
    private Long id;
    private String notice_writer;
    private String notice_title;
    private String notice_content;

    public Notice toEntity() {
        return new Notice(id, notice_writer, notice_title, notice_content);
    }
}
