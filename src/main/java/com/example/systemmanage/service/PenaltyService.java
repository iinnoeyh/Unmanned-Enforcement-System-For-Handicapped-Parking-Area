package com.example.systemmanage.service;

import com.example.systemmanage.dto.PenaltyDto;
import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.entity.Penalty;
import com.example.systemmanage.repository.ManageRepository;
import com.example.systemmanage.repository.PenaltyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PenaltyService {
    @Autowired
    private PenaltyRepository penaltyRepository;
    @Autowired
    private ManageRepository manageRepository;

    public List<PenaltyDto> penalties(Long manageId) {
//        // 1. 페널티 조회
//        List<Penalty> penalties = penaltyRepository.findByManageId(manageId);
//        // 2. 엔티티 -> DTO 변환
//        List<PenaltyDto> dtos = new ArrayList<PenaltyDto>();
//        for(int i = 0; i < penalties.size(); i++){  // 조회된 페널티 엔티티 수만큼 반복
//            Penalty p = penalties.get(i);   // 조회된 페널티 엔티티 하나씩 가져오기
//            PenaltyDto dto = PenaltyDto.createPenaltyDto(p);    // 엔티티를 DTO로 변환
//            dtos.add(dto);  // 변환한 DTO를 dtos 리스트에 삽입
//        }
        // 3. 결과 반환
        return penaltyRepository.findByManageId(manageId)   // 페널티 엔티티 목록 조회
                .stream()   // 페널티 엔티티 목록을 스트림으로 변환
                .map(penalty -> PenaltyDto.createPenaltyDto(penalty))   // 엔티티를 DTO로 매핑
                .collect(Collectors.toList());  // 스트림을 리스트로 변환
    }

    @Transactional
    public PenaltyDto create(Long manageId, PenaltyDto dto) {
        // 1. 사용자 조회 및 예외 발생
        Manage manage = manageRepository.findById(manageId)
                .orElseThrow(() -> new IllegalArgumentException(("페널티 생성 실패" + "대상 유저가 없습니다.")));
        // 2. 페널티 엔티티 생성
        Penalty penalty = Penalty.createPemalty(dto, manage);
        // 엔터 키를 줄바꿈 문자로 저장
        String body = penalty.getBody().replace("\n", System.lineSeparator());
//        log.info(penalty.toString());
        // 3. 페널티 엔티티를 DB에 저장
        Penalty created = penaltyRepository.save(penalty);
//        log.info(created.toString());
        // 4. DTO로 변환해 반환
        return PenaltyDto.createPenaltyDto(created);
    }

    @Transactional
    public PenaltyDto update(Long id, PenaltyDto dto) {
        // 1. 사용자 조회 및 예외 발생
        Penalty target = penaltyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("페널티 수정 실패!" + "대상 유저가 없습니다."));
        // 2. 페널티 수정
        target.patch(dto);
        // 3. DB로 갱신
        Penalty updated = penaltyRepository.save(target);
        // 4. 페널티 엔티티를 DTO로 변환 및 반환
        return PenaltyDto.createPenaltyDto(updated);
    }

    @Transactional
    public PenaltyDto delete(Long id) {
        // 페널티 조회 및 예외 발생
        Penalty target = penaltyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("페널티 삭제 실패!" + "대상이 없습니다"));
        // 페널티 삭제
        penaltyRepository.delete(target);
        // 삭제 페널티를 DTO로 변환 및 반환
        return PenaltyDto.createPenaltyDto(target);
    }
}
