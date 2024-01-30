package com.example.systemmanage.api;

import com.example.systemmanage.dto.ArticleDto;
import com.example.systemmanage.dto.NoticeDto;
import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Notice;
import com.example.systemmanage.repository.NoticeRepository;
import com.example.systemmanage.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class NoticeApiController {
    @Autowired
    private NoticeService noticeService;
    // GET
    @GetMapping("/api/notices")
    public List<Notice> index(){   // 모든 게시글 조회
        return noticeService.index();
    }
    @GetMapping("/api/notices/{id}")
    public Notice show(@PathVariable Long id){
        return noticeService.show(id);
    }
    // POST
    @PostMapping("/api/notices")   // url 요청 접수
    public ResponseEntity<Notice> create(@RequestBody NoticeDto dto){
        Notice created = noticeService.create(dto);
        return (created != null) ?  // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // PATCH
    @PatchMapping("/api/notices/{id}")
    public ResponseEntity<Notice> update(@PathVariable Long id, @RequestBody NoticeDto dto){
        Notice updated = noticeService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // DELETE
    @DeleteMapping("/api/notices/{id}")
    public ResponseEntity<Notice> delete(@PathVariable Long id){
        Notice deleted = noticeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
