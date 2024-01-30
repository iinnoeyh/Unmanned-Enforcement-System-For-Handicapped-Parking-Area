package com.example.systemmanage.repository;

import com.example.systemmanage.entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
    // 특정 사람의 모든 페널티 조회
    @Query(value = "SELECT * FROM penalty WHERE manage_id = :manageId", nativeQuery = true)
    List<Penalty> findByManageId(Long manageId);
}
