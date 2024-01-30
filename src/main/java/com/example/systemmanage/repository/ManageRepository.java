package com.example.systemmanage.repository;

import com.example.systemmanage.entity.Manage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ManageRepository extends JpaRepository<Manage, Long> {
    @Override
    ArrayList<Manage> findAll();
    List<Manage> findByCarContaining(String keyword);
//    Manage findByCar(String car);
//    Page<Manage> findByCarNumberContaining(String car_number, Pageable pageable);
//    Page<Manage> findByCarNumberContaining(String keyword, Pageable pageable);
}
