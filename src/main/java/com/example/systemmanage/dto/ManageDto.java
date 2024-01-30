package com.example.systemmanage.dto;

import com.example.systemmanage.entity.Manage;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ManageDto {
    private Long id;
    private String name;
    private String phone_number;
    private String car;
    private String address;
    private String front_resident_num;
    private String back_resident_num;
    private Boolean disabled;

    public Manage toEntity() {
        return new Manage(id, name, phone_number, car, address, front_resident_num, back_resident_num, disabled);
    }
}
