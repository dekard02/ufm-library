package com.ufm.library.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class LibrarianDto {
    private Long id;
    private String fullname;
    private String username;
    private String citizenId;
    private String photo;
    private String phoneNumber;
    private LocalDateTime dateOfBirth;
    private LocalDateTime createdAt;
    private Boolean active;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends LibrarianDto {
        private String role;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends LibrarianDto {
        private Long role;
    }

    @Data
    public static class CommonField {
        private Long id;
        private String fullname;
    }
}
