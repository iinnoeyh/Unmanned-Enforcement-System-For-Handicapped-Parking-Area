package com.example.systemmanage.dto;

import com.example.systemmanage.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 생성자
@ToString
public class CustomerDto {
    private Long num;
    private String name;
    private String id;
    private String password;
    private String phone_number;
    private String car_number;
    private String front_resident_num;
    private String back_resident_num;
    private String address;

    public Customer toEntity() {
        return new Customer(num, name, id, password, phone_number, car_number, front_resident_num, back_resident_num, address);
    }
}
