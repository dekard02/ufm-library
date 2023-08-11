package com.ufm.library.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class StudentDto {
    private String id;

    private String fullname;

    private String address;

    private String email;

    private LocalDateTime dateOfBirth;

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
    public static class Request extends StudentDto {
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
