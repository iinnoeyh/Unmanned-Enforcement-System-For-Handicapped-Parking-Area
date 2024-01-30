package com.example.systemmanage.api;

import com.example.systemmanage.dto.ArticleDto;
import com.example.systemmanage.dto.CustomerDto;
import com.example.systemmanage.entity.Article;
import com.example.systemmanage.entity.Customer;
import com.example.systemmanage.repository.CustomerRepository;
import com.example.systemmanage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApiController {
//    @Autowired
//    private CustomerService customerService;
//    private CustomerRepository customerRepository;


    // GET
    // PATCH
//    @PatchMapping("/api/user/info")
//    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody CustomerDto dto){
//        Customer updated = customerService.update(id, dto);
//        return (updated != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(updated) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
    // PATCH
    // DELETE
}
