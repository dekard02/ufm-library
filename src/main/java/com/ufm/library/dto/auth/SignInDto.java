package com.ufm.library.dto.auth;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Sign in")
public class SignInDto {

    @NotBlank(message = "Tài khoản không được bỏ trống")
    private String username;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;
}
