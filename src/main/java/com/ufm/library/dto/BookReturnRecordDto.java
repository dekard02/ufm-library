package com.ufm.library.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BookReturnRecordDto {
    private Long id;

    private Double fine;

    private String note;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InLoanRecord extends BookReturnRecordDto {
        private LocalDateTime returnDate;

        private Long librarianId;

        private String librarianName;

        private Long bookLoanRecordId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetailResponse extends BookReturnRecordDto {
        private LibrarianDto.CommonField librarian;

        private Long bookLoanRecordId;

        private LocalDateTime returnDate;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends BookReturnRecordDto {
        private Long librarian;

        private Long bookLoanRecord;
    }
}
