package com.example.systemmanage.controller;

import com.example.systemmanage.dto.NoticeDto;
import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.entity.Notice;
import com.example.systemmanage.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class NoticeController {
    @Autowired
    private NoticeRepository noticeRepository;

//    @GetMapping("/notices")  // 상세 페이지
//    public String index(Model model, Pageable pageable, @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) { // @RequestParam(name = "keyword", required = false)
//        try {
//            List<Notice> noticeEntityList;
//
//            if (keyword != null && !keyword.isEmpty()) {
//                // 검색어가 제공된 경우, 해당 검색어로 데이터를 검색
//                noticeEntityList = noticeRepository.findByNotice_TitleContaining(keyword);
//            } else {
//                // 검색어가 없는 경우, 모든 데이터 가져오기
//                noticeEntityList = noticeRepository.findAll();
//            }
//
//            // 검색 결과를 모델에 추가
//            model.addAttribute("noticeList", noticeEntityList);
//            model.addAttribute("keyword", keyword); // 검색어를 모델에 추가
//
//            // 뷰 페이지 설정
//            return "notices/notice";
//        } catch (Exception e) {
//            // 예외 처리: 데이터 조회 중에 오류 발생 시
//            log.error("데이터 조회 중 오류 발생: " + e.getMessage(), e);
//            // 오류 페이지로 이동하거나 다른 오류 처리 방법을 선택하세요
//            return "error"; // 에러 페이지로 이동
//        }
//    }

    // 조회
    @GetMapping("/notices")    // url 요청
    public String noticeForm(Model model){
        // 1. 모든 데이터 가져오기
        List<Notice> noticeEntityList = noticeRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("noticeList", noticeEntityList);
        // 3. 뷰 페이지 설정하기
        return "notices/notice";
    }
    // 수정
    @GetMapping("/notices/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Notice noticeEntity = noticeRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("notice", noticeEntity);
        // 뷰 페이지
        return "notices/notice_edit";
    }

    // 수정 폼 업데이트
    @PostMapping("/notices/update")
    public String update(NoticeDto dto){ // 매개변수로 DTO 받아오기
        log.info(dto.toString());
        // 1. DTO를 엔티티로 변환
        Notice noticeEntity = dto.toEntity();    // DTO를 엔티티로 변환
        log.info(noticeEntity.toString());

        // 2. 엔티티를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Notice target = noticeRepository.findById(noticeEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값 갱신하기
        if(target != null){
            noticeRepository.save(noticeEntity);    // 엔티티를 DB에 저장(갱신)
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/notices/" + noticeEntity.getId();
    }

    // 삭제
    @GetMapping("/notices/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("공지사항: 삭제 요청이 들어왔습니다.");
        // 1. 삭제할 대상 가져오기
        Notice target = noticeRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if(target != null){
            noticeRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제 완료!.");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/notices";
    }

    @GetMapping("/notices/new")    // url 요청
    public String newNoticeForm(){
        return "notices/notice_new";
    }

    @PostMapping("/notices/create")    // url 요청
    public String createNotice(NoticeDto form){  // 메서드 생성 및 반환값 작성
        log.info(form.toString());
//        System.out.println(form.toString());
        // 1. DTO를 엔티티로 변환
        Notice notice =form.toEntity();
        log.info(notice.toString());
//        System.out.println(notice.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Notice saved = noticeRepository.save(notice);    // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "redirect:/notices/" + saved.getId();
    }


    @GetMapping("/notices/{id}")   // 데이터 조회 요청 함수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Notice noticeEntity = noticeRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("notice", noticeEntity);
        // 3. 뷰 페이지 반환하기
        return "notices/notice_show";
    }
}
