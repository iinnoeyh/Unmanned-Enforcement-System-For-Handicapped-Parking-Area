package com.example.systemmanage.service;

import com.example.systemmanage.dto.NoticeDto;
import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Notice;
import com.example.systemmanage.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> index() {
        return noticeRepository.findAll();
    }

    public Notice show(Long id) {
        return noticeRepository.findById(id).orElse(null);
    }

    public List<Notice> showTop3(){
        return noticeRepository.findTop3ByOrderByIdDesc();
    }

    public Notice create(NoticeDto dto) {
        Notice notice = dto.toEntity();
        if(notice.getId() != null){
            return null;
        }
        return noticeRepository.save(notice);
    }

    public Notice update(Long id, NoticeDto dto) {
        // 1. DTO -> 엔티티 변환
        Notice notice = dto.toEntity();
        log.info("id: {}, notice:{}", id, notice.toString());
        // 2. 타깃 조회
        Notice target = noticeRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        if(target == null || id != notice.getId()){
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, notice: {}", id, notice.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(notice);  // 기존 데이터에 새 데이터 붙이기
        Notice updated = noticeRepository.save(target);
        return updated;
    }

    public Notice delete(Long id) {
        // 1. 대상 찾기
        Notice target = noticeRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        // 3. 대상 삭제하기
        noticeRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
        return target;
    }
}
