package com.ufm.library.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Category")
public class CategoryDto {

    @Hidden
    private Long id;

    @Size(max = 40, message = "Tên loại sách không được quá 40 ký tự")
    @NotNull(message = "Trường name không được bỏ trống")
    private String name;

    private Boolean isDeleted;
}
