package com.example.systemmanage.repository;

import com.example.systemmanage.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
    List<Article> findTop3ByOrderByIdDesc();
//    ArrayList<Article> findTop3ByOrderByDesc();
}
