package com.ufm.library.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    private Long id;

    @Length(max = 40, message = "Tên loại sách không được quá 40 ký tự")
    @NotNull(message = "Trường name không được bỏ trống")
    private String name;

    private Boolean isDeleted;
}
