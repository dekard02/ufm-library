package com.ufm.library.dto.auth;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ChangePasswordDto {

    @NotBlank(message = "Mật khẩu hiện tại không được bỏ trống")
    private String currentPassword;

    @NotBlank(message = "Mật khẩu mới không được bỏ trống")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$", message = "Password phải có ít nhất 8 kí tự, có tối thiểu 1 chữ cái viết hoa, 1 chữ cái viết thường, 1 chữ số")
    private String password;

    @NotBlank(message = "Xác nhận mật khẩu không được bỏ trống")
    private String passwordConfirm;

    @AssertTrue(message = "Mật khẩu và xác nhận mật khẩu phải khớp nhau")
    private boolean isPasswordConfirm() {
        return this.password.equals(this.passwordConfirm);
    }
}
