package com.example.systemmanage.api;

import com.example.systemmanage.dto.ArticleDto;
import com.example.systemmanage.dto.ManageDto;
import com.example.systemmanage.dto.NoticeDto;
import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.entity.Notice;
import com.example.systemmanage.repository.ManageRepository;
import com.example.systemmanage.service.ManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ManageApiController {
    @Autowired
    private ManageService manageService;  // manageRepository 객체 선언
    // GET
    @GetMapping("/api/manage")
    public List<Manage> index(){
        return manageService.index();
    }

    @GetMapping("/api/manage/{id}")
    public Manage show(@PathVariable Long id){
        return manageService.show(id);
    }
    // POST
    @PostMapping("/api/manage")   // url 요청 접수
    public ResponseEntity<Manage> create(@RequestBody ManageDto dto){
        Manage created = manageService.create(dto);
        return (created != null) ?  // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // PATCH
    @PatchMapping("/api/manage/{id}")
    public ResponseEntity<Manage> update(@PathVariable Long id, @RequestBody ManageDto dto){
        Manage updated = manageService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    // DELETE
    @DeleteMapping("/api/manage/{id}")
    public ResponseEntity<Manage> delete(@PathVariable Long id){
        Manage deleted = manageService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
