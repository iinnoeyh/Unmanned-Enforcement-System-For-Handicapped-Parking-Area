package com.example.systemmanage.repository;

import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.entity.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice, Long> {
    @Override
    ArrayList<Notice> findAll();
    List<Notice> findTop3ByOrderByIdDesc();
//    List<Notice> findByNotice_TitleContaining(String keyword);
}
