package com.ufm.library.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Location")
public class LocationDto {

    @Hidden
    private Long id;

    @Size(max = 150, message = "Tên cơ sở không được quá 150 ký tự")
    private String name;

    @Size(max = 150, message = "Địa chỉ không được quá 150 ký tự")
    @NotBlank(message = "Trường address không được bỏ trống")
    private String address;

    @Size(max = 15, message = "Số điện thoại không được quá 15 ký tự")
    private String phoneNumber;

    private Boolean isDeleted;
}
