package com.example.systemmanage.controller;

import com.example.systemmanage.dto.CustomerDto;
import com.example.systemmanage.dto.PenaltyDto;
import com.example.systemmanage.entity.Customer;
import com.example.systemmanage.entity.Manage;
import com.example.systemmanage.repository.CustomerRepository;
import com.example.systemmanage.service.PenaltyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j  // 로깅 기능을 위한 어노테이션
@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PenaltyService penaltyService;  // 서비스 객체 주입

//    @GetMapping("/home")
//    public String findId1(Model model){
//        try {
//            Long id = 1L; // Convert the int value 1 to a Long
//            Optional<Customer> customerOptional = customerRepository.findById(id);
//
//            if (customerOptional.isPresent()) {
//                Customer customerEntity = customerOptional.get();
//                model.addAttribute("customerEntity", customerEntity);
//                return "home";
//            } else {
//                return "error"; // Data not found
//            }
//        } catch (Exception e) {
//            log.error("Error while retrieving data: " + e.getMessage(), e);
//            return "error"; // Error handling
//        }
//    }

//    @GetMapping("/user/info")    // url 요청
//    public String usersForm(){
//        return "users/user_info";
//    }

    @GetMapping("/user/info")    // url 요청
    public String usersForm(Model model){
        try {
            Long id = 1L; // Convert the int value 1 to a Long
            Optional<Customer> customerOptional = customerRepository.findById(id);

            if (customerOptional.isPresent()) {
                Customer customerEntity = customerOptional.get();
                model.addAttribute("customerEntity", customerEntity);
                return "users/user_info";
            } else {
                return "error"; // Data not found
            }
        } catch (Exception e) {
            log.error("Error while retrieving data: " + e.getMessage(), e);
            return "error"; // Error handling
        }
        // return "users/user_info";
    }


//    @GetMapping("/user/penalty")    // url 요청
//    public String usersPenaltyForm(){
//        return "users/user_penalty";
//    }

    @GetMapping("/user/penalty")    // url 요청
    public String usersPenaltyForm(Model model){
        try {
            Long id = 1L; // Convert the int value 1 to a Long
            Optional<Customer> customerOptional = customerRepository.findById(id);
            // id #1 페널티
            List<PenaltyDto> penaltyDtos = penaltyService.penalties(id);

            if (customerOptional.isPresent()) {
                Customer customerEntity = customerOptional.get();
                model.addAttribute("customerEntity", customerEntity);
                model.addAttribute("customerPenaltyDtos",penaltyDtos);
                return "users/user_penalty";
            } else {
                return "error"; // Data not found
            }
        } catch (Exception e) {
            log.error("Error while retrieving data: " + e.getMessage(), e);
            return "error"; // Error handling
        }
    }

    @GetMapping("/login")    // url 요청
    public String usersLoginForm(){
        return "users/user_login";
    }

    @GetMapping("/signup")    // url 요청
    public String signUpForm(){
        return "users/user_signup";
    }

    @PostMapping("/signup")
    public String usersSignUp(CustomerDto form){
        // 회원가입 과정
        log.info(form.toString());
        // DTO를 엔티티로 변환
        Customer user = form.toEntity();
        log.info(user.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Customer saved = customerRepository.save(user);
        log.info(saved.toString());
        return "dashboard";
    }
}
