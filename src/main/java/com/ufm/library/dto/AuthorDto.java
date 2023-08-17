package com.ufm.library.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Author")
public class AuthorDto {

    @Hidden
    private Long id;

    @NotBlank(message = "Trường fullname tên không được bỏ trống")
    @Size(max = 150, message = "Họ và tên không được dài quá 150 ký tự")
    private String fullname;

    private Integer yearOfBirth;

    private Boolean isDeleted;
}
