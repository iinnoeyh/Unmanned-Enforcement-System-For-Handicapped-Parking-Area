package com.example.systemmanage.controller;

import com.example.systemmanage.dto.ManageDto;
import com.example.systemmanage.dto.PenaltyDto;
import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.repository.ManageRepository;
import com.example.systemmanage.service.PenaltyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ManageController {
    @Autowired
    private ManageRepository manageRepository;  // manageRepository 객체 선언
    @Autowired
    private PenaltyService penaltyService;  // 서비스 객체 주입

    @GetMapping("/manage")  // 상세 페이지
    public String index(Model model, Pageable pageable, @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) { // @RequestParam(name = "keyword", required = false)
        try {
            List<Manage> manageEntityList;

            if (keyword != null && !keyword.isEmpty()) {
                // 검색어가 제공된 경우, 해당 검색어로 데이터를 검색
                manageEntityList = manageRepository.findByCarContaining(keyword);
            } else {
                // 검색어가 없는 경우, 모든 데이터 가져오기
                manageEntityList = manageRepository.findAll();
            }

            // 검색 결과를 모델에 추가
            model.addAttribute("manageList", manageEntityList);
            model.addAttribute("keyword", keyword); // 검색어를 모델에 추가

            // 뷰 페이지 설정
            return "manage";
        } catch (Exception e) {
            // 예외 처리: 데이터 조회 중에 오류 발생 시
            log.error("데이터 조회 중 오류 발생: " + e.getMessage(), e);
            // 오류 페이지로 이동하거나 다른 오류 처리 방법을 선택하세요
            return "error"; // 에러 페이지로 이동
        }
    }


//    @GetMapping("/manage")  // 상세 페이지
//    public String index(Model model){
//        try {
//            // 1. 모든 데이터 가져오기
//            List<Manage> manageEntityList = manageRepository.findAll();
//            // 2. 모델에 데이터 등록하기
//            model.addAttribute("manageList", manageEntityList);
//            // 3. 뷰 페이지 설정하기
//            return "manage";
//        } catch (Exception e) {
//            // 예외 처리: 데이터 조회 중에 오류 발생 시
//            log.error("데이터 조회 중 오류 발생: " + e.getMessage(), e);
//            // 오류 페이지로 이동하거나 다른 오류 처리 방법을 선택하세요
//            return "error"; // 에러 페이지로 이동
//        }
//    }

    @GetMapping("/manage/{id}")
    public String showManage(@PathVariable Long id, Model model){    // 매개변수로 id 받아오기
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Manage manageEntity = manageRepository.findById(id).orElse(null);
        List<PenaltyDto> penaltyDtos = penaltyService.penalties(id);

//        String penaltyBody = HtmlUtils.htmlEscape(penaltyDtos.fin)
        // 2. 모델에 데이터 등록하기
        model.addAttribute("manageShow", manageEntity);
        model.addAttribute("penaltyDtos",penaltyDtos);
        // 3. 뷰 페이지 반환하기
        return "manages/manage_show";
    }

//    @GetMapping("/manage/{id}/edit")
//    public String editManage(@PathVariable Long id, Model model){    // 매개변수로 id 받아오기
//        // 1. 수정할 데이터 가져오기
//        Manage manageEntity = manageRepository.findById(id).orElse(null);
//        // 2. 모델에 데이터 등록하기
//        model.addAttribute("manageEdit", manageEntity);
//        // 3. 뷰 페이지 설정
//        return "manages/manage_show";
//    }


    @PostMapping("/manage/update")
    public String update(ManageDto dto, @RequestParam(value = "disabled", required = false) Boolean disabled){
        // 1. DTO를 엔티티로 변환
        Manage manageEntity = dto.toEntity();
        log.info(manageEntity.toString());
        // 2. 엔티티를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Manage target = manageRepository.findById(manageEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            // 체크박스의 상태에 따라 boolean 값 설정
            if (disabled != null && disabled) {
                manageEntity.setDisabled(true);
            } else {
                manageEntity.setDisabled(false);
            }
            manageRepository.save(manageEntity);    // 엔티티 DB에 갱신
        }
        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/manage/" + manageEntity.getId();
    }

    @PostMapping("/manage")
    public String createInfo(ManageDto form, @RequestParam(value = "disabled", name="disabled", required = false, defaultValue = "false") Boolean disabled) {
        try {
            log.info((form.toString()));
            // 1. DTO를 엔티티로 변환
            Manage manage = form.toEntity();
            log.info(manage.toString());
            // 2. 리파지터리로 엔티티를 DB에 저장
            // 체크박스의 상태에 따라 boolean 값 설정
            if (disabled != null && disabled) {
                manage.setDisabled(true);
            } else {
                manage.setDisabled(false);
            }
            Manage saved = manageRepository.save(manage);
            log.info(saved.toString());
            return "redirect:/manage"; // 데이터 저장 후 manage 페이지로 리다이렉트
        } catch (Exception e) {
            // 예외 처리: 데이터 저장 중에 오류 발생 시
            log.error("데이터 저장 중 오류 발생: " + e.getMessage(), e);
            // 오류 페이지로 이동하거나 다른 오류 처리 방법을 선택하세요
            return "error"; // 에러 페이지로 이동
        }
    }

    @GetMapping("/manage/{id}/delete")
    public String deleteManage(@PathVariable Long id){
        log.info("삭제 요청이 들어왔습니다.");
        // 1. 삭제할 대상
        Manage target = manageRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제
        if(target != null){
            manageRepository.delete(target);
        }
        // 3. 결과 페이지로 리다이렉트
        return "redirect:/manage";
    }

//    @PostMapping("/manage")
//    public String createInfo(ManageDto form, @RequestParam(value = "disabled", required = false) Boolean disabled) {
//        try {
//            log.info((form.toString()));
//            // 1. DTO를 엔티티로 변환
//            Manage manage = form.toEntity();
//            log.info(manage.toString());
//            // 1-2. 체크박스의 상태에 따라 boolean 값 설정
//            if (disabled != null && disabled) {
//                manage.setDisabled(true);
//            } else {
//                manage.setDisabled(false);
//            }
//            // 2. 리파지터리로 엔티티를 DB에 저장
//            Manage saved = manageRepository.save(manage);
//            log.info(saved.toString());
//            return "redirect:/manage"; // 데이터 저장 후 manage 페이지로 리다이렉트
//        } catch (Exception e) {
//            // 예외 처리: 데이터 저장 중에 오류 발생 시
//            log.error("데이터 저장 중 오류 발생: " + e.getMessage(), e);
//            // 오류 페이지로 이동하거나 다른 오류 처리 방법을 선택하세요
//            return "error"; // 에러 페이지로 이동
//        }
//    }

//    @DeleteMapping("/manage/{id}")
//    public String delete(@PathVariable Long id){
//        log.info("삭제 요청");
//        // 1. 삭제할 대상 가져오기
//        Manage target = manageRepository.findById(id).orElse(null);
//        // 2. 대상 엔티티 삭제
//        if(target != null){
//            manageRepository.delete(target);
//        }
//        return "redirect:/manage"; // 데이터 저장 후 manage 페이지로 리다이렉트
//    }

//    @DeleteMapping("/manage")
//    public String delete(@PathVariable Long id){
//        log.info("삭제 요청");
//        // 1. 삭제할 대상 가져오기
//        Manage target = manageRepository.findById(id).orElse(null);
////        log.info(target.toString());
//        // 2. 대상 엔티티 삭제
//        if(target != null){
//            manageRepository.delete(target);
//        }
//        return "redirect:/manage"; // 데이터 저장 후 manage 페이지로 리다이렉트
//    }




}
