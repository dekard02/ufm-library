package com.ufm.library.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class LibrarianDto {
    private Long id;
    private String fullname;
    private String username;
    private String citizenId;
    private String phoneNumber;
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
        private Long role;
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
