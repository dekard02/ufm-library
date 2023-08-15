package com.ufm.library.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {
    private Long id;

    @NotBlank(message = "Trường fullname tên không được bỏ trống")
    @Length(max = 150, message = "Họ và tên không được dài quá 150 ký tự")
    private String fullname;

    private Integer yearOfBirth;

    private Boolean isDeleted;
}
