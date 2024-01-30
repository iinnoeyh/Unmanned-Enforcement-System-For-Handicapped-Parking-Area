package com.example.systemmanage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자 추가 어노테이션
@ToString
@Entity
@Getter
public class Manage {
    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone_number", nullable = false)
    private String phone_number;
    @Column(name = "car")
    private String car;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "front_resident_num", nullable = false)
    private String front_resident_num;
    @Column(name = "back_resident_num", nullable = false)
    private String back_resident_num;
    @Column(name = "disabled")
    private Boolean disabled;


    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void patch(Manage manage) {
        if(manage.name != null)
            this.name = manage.name;
        if(manage.phone_number != null)
            this.phone_number = manage.phone_number;
        if(manage.car != null)
            this.car = manage.car;
        if(manage.address != null)
            this.address = manage.address;
        if(manage.front_resident_num != null)
            this.front_resident_num = manage.front_resident_num;
        if(manage.back_resident_num != null)
            this.back_resident_num = manage.back_resident_num;
        if(manage.disabled != null)
            this.disabled = manage.disabled;
    }
}
