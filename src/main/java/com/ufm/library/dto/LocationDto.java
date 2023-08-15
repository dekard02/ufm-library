package com.ufm.library.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
    private Long id;

    @Length(max = 150, message = "Tên cơ sở không được quá 150 ký tự")
    private String name;

    @Length(max = 150, message = "Địa chỉ không được quá 150 ký tự")
    @NotBlank(message = "Trường address không được bỏ trống")
    private String address;

    @Length(max = 15, message = "Số điện thoại không được quá 15 ký tự")
    private String phoneNumber;

    private Boolean isDeleted;
}
