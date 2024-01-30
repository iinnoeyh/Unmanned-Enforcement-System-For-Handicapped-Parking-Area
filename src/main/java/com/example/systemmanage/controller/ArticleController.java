package com.example.systemmanage.controller;

import com.example.systemmanage.dto.ArticleDto;
import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Notice;
import com.example.systemmanage.repository.ArticleRepository;
import com.example.systemmanage.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j  // 로깅 기능을 위한 어노테이션
@Controller
public class ArticleController {
    @Autowired  // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private ArticleRepository articleRepository;    // articleRepository 객체 선언
    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/home")
    public String homeForm(Model model){
        List<Article> articleList = articleRepository.findTop3ByOrderByIdDesc();
        model.addAttribute("articleTop3", articleList);
        List<Notice> noticeList = noticeRepository.findTop3ByOrderByIdDesc();
        model.addAttribute("noticeTop3", noticeList);
        return "dashboard/dashboard";
    }

    @GetMapping("/articles")    // url 요청
    public String articleForm(Model model){
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/article";
    }

    // 수정
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){  // id를 매개변수로 받아오기
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정
        return "articles/article_edit";
    }

    // 수정 폼 업데이트
    @PostMapping("/articles/update")
    public String update(ArticleDto dto){  // 매개변수로 DTO 받아오기
        log.info(dto.toString());
        // 1. DTO를 엔티티로 변환
        Article articleEntity = dto.toEntity(); // DTO를 엔티티로 변환
        log.info(articleEntity.toString());

        // 2. 엔티티를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값 갱신하기
        if(target != null){
            articleRepository.save(articleEntity);    // 엔티티를 DB에 저장(갱신)
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    // 삭제
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("게시판: 삭제 요청이 들어왔습니다.");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제 완료!.");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }

    // 추가
    @GetMapping("/articles/new")    // url 요청
    public String newArticleForm(){
        return "articles/article_new";
    }

    // 저장
    @PostMapping("/articles/create")    // url 요청
    public String createArticle(ArticleDto form){  // 메서드 생성 및 반환값 작성
        log.info(form.toString());
//        System.out.println(form.toString());
        // 1. DTO를 엔티티로 변환
        Article article =form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);    // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")   // 데이터 조회 요청 함수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/article_show";
    }
}
