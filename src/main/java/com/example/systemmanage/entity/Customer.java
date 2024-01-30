package com.example.systemmanage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자 추가 어노테이션
@ToString
@Entity
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    @Column(name = "num", updatable = false)
    private Long num;
    @Column(name = "name")
    private String name;
    @Column(name = "id")
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "car_number")
    private String car_number;
    @Column(name = "front_resident_num")
    private String front_resident_num;
    @Column(name = "back_resident_num")
    private String back_resident_num;
    @Column(name = "address")
    private String address;

//    public void patch(Customer customer) {
//        if(customer.name != null)
//            this.name = customer.name;
//        if(customer.id != null)
//            this.id = customer.id;
//        if(customer.password != null)
//            this.password = customer.password;
//        if(customer.phone_number != null)
//            this.phone_number = customer.phone_number;
//        if(customer.car_number != null)
//            this.car_number = customer.car_number;
//        if(customer.address != null)
//            this.address = customer.address;
//        if(customer.front_resident_num != null)
//            this.front_resident_num = customer.front_resident_num;
//        if(customer.back_resident_num != null)
//            this.back_resident_num = customer.back_resident_num;
//        if(customer.address != null)
//            this.address = customer.address;
//    }
}
