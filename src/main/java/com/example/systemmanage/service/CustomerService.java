package com.example.systemmanage.service;

import com.example.systemmanage.dto.CustomerDto;
import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Customer;
import com.example.systemmanage.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    public Customer update(Long id, CustomerDto dto) {
//        // 1. DTO -> 엔티티 변환
//        Customer customer = dto.toEntity();
//        log.info("id: {}, customer:{}", id, customer.toString());
//        // 2. 타깃 조회
//        Customer target = customerRepository.findById(id).orElse(null);
//        // 3. 잘못된 요청 처리
//        if(target == null || id != customer.getId()){
//            // 400, 잘못된 요청 응답
//            log.info("잘못된 요청! id: {}, article: {}", id, customer.toString());
//            return null;
//        }
//        // 4. 업데이트 및 정상 응답(200)하기
//        target.patch(customer);  // 기존 데이터에 새 데이터 붙이기
//        Customer updated = customerRepository.save(target);
//        return updated;
//    }
}
