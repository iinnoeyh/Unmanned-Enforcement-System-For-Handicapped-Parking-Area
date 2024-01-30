package com.example.systemmanage.repository;

import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.entity.Penalty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PenaltyRepositoryTest {
    @Autowired
    PenaltyRepository penaltyRepository;
    @Test
    @DisplayName("특정 사람의 모든 페널티 조회")
    void findByManageId() {
//        case 1: 1번 사람의 모든 페널티 조회
        {
            // 1. 입력 데이터 준비
            Long manageId = 1L;
            // 2. 실제 데이터
            List<Penalty> penalties = penaltyRepository.findByManageId(manageId);
            // 3. 예상 데이터
            Manage manage = new Manage(1L, "홍길동", "01011111111", "12가 1234", 0, "990101", "1111111", false);
            Penalty a = new Penalty(1L, manage, "5분 이상, 불법 주차가 확인되었습니다.");
            Penalty b = new Penalty(2L, manage, "5분 이상, 불법 주차가 확인되었습니다.");
            List<Penalty> expected = Arrays.asList(a, b);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), penalties.toString(), "1번 사람의 모든 페널티 출력");
        }
    }
}