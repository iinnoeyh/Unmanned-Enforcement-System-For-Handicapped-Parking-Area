package com.example.systemmanage.service;

import com.example.systemmanage.dto.ManageDto;
import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.repository.ManageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ManageService {
    @Autowired
    private ManageRepository manageRepository;

    public List<Manage> index() {
        return manageRepository.findAll();
    }

    public Manage show(Long id) {
        return manageRepository.findById(id).orElse(null);
    }
    public List<Manage> search(String keyword){
        return manageRepository.findByCarContaining(keyword);}

//    public Page<Manage> carNumberSearchList(String car_number, Pageable pageable){
//        return manageRepository.findByCarNumberContaining(car_number, pageable);}

//    public Page<Manage> findByCarNumber(String keyword, Pageable pageable){
//        return manageRepository.findByCarNumberContaining(keyword, pageable);
//    }


    public Manage create(ManageDto dto) {
        Manage manage = dto.toEntity();
        if(manage.getId() != null){
            return null;
        }
        return manageRepository.save(manage);
    }

    public Manage update(Long id, ManageDto dto) {
        // 1. DTO -> 엔티티 변환
        Manage manage = dto.toEntity();
        log.info("id: {}, manage:{}", id, manage.toString());
        // 2. 타깃 조회
        Manage target = manageRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        if(target == null || id != manage.getId()){
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, manage: {}", id, manage.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(manage);  // 기존 데이터에 새 데이터 붙이기
        Manage updated = manageRepository.save(target);
        return updated;
    }

    public Manage delete(Long id) {
        // 1. 대상 찾기
        Manage target = manageRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        // 3. 대상 삭제하기
        manageRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
        return target;
    }
}
