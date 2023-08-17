package com.ufm.library.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufm.library.validation.annotation.IsImage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class StudentDto {

    private String id;

    @Size(max = 150, message = "Họ và tên không được quá 150 ký tự")
    @NotBlank(message = "Trường fullname không được bỏ trống")
    private String fullname;

    @Size(max = 150, message = "Họ và tên không được quá 150 ký tự")
    @NotBlank(message = "Trường fullname không được bỏ trống")
    private String address;

    @Size(max = 150, message = "Email không được quá 150 ký tự")
    @NotBlank(message = "Trường email không được bỏ trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @Past(message = "Ngày sinh phải là ngày ở quá khứ")
    private LocalDateTime dateOfBirth;

    @Size(max = 15, message = "Số điện thoại không được quá 15 ký tự")
    @NotBlank(message = "Trường phoneNumber không được bỏ trống")
    private String phoneNumber;

    private Boolean gender;

    private Boolean isDeleted;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends StudentDto {
        private String photo;
        private LocalDateTime createdAt;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "Student")
    public static class Request extends StudentDto {

        @NotBlank(message = "Mã sinh viên không được bỏ trống")
        @Size(min = 10, max = 10, message = "Mã sinh viên phải có 10 ký tự")
        private String id;

        @IsImage
        private MultipartFile photo;
    }

    @Data
    public static class CommonField {
        private String id;
        private String fullname;
        private String address;
        private String email;
    }
}
