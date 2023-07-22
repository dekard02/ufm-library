package com.ufm.library.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BookReturnRecordDto {
    private Long id;

    private LocalDateTime returnDate;

    private Double fine;

    private String note;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends LibrarianDto {
        private LibrarianDto.CommonField librarian;

        private Long bookLoanRecordId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends LibrarianDto {
        private Long librarian;

        private Long bookLoanRecord;
    }
}
