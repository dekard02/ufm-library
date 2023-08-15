package com.ufm.library.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufm.library.entity.Role;
import com.ufm.library.validation.annotation.Exist;
import com.ufm.library.validation.annotation.IsImage;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class LibrarianDto {
    private Long id;

    @Length(max = 150, message = "Họ và tên không được quá 150 ký tự")
    @NotBlank(message = "Trường fullname không được bỏ trống")
    private String fullname;

    @Length(max = 100, message = "Tên tài khoản không được quá 100 ký tự")
    @NotBlank(message = "Trường username không được bỏ trống")
    private String username;

    @Length(max = 15, message = "Số CCCD không được quá 15 ký tự")
    @NotBlank(message = "Trường citizenId không được bỏ trống")
    private String citizenId;

    @Length(max = 15, message = "Số điện thoại không được quá 15 ký tự")
    private String phoneNumber;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDateTime dateOfBirth;

    private Boolean active;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends LibrarianDto {
        private RoleDto role;
        private String photo;
        private LocalDateTime createdAt;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends LibrarianDto {

        @Exist(entityClass = Role.class, message = "Không tồn tại chức vụ với mã {}")
        @NotNull(message = "Trường role không được bỏ trống")
        private Long role;

        @IsImage
        private MultipartFile photo;
    }

    @Data
    public static class CommonField {
        private Long id;
        private String fullname;
        private String username;
        private String phoneNumber;
        private String role;
    }
}
