package com.ufm.library.dto.auth;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String username;
    private String currentPassword;
    private String password;
    private String passwordConfirm;
}
