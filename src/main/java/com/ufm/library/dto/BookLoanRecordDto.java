package com.ufm.library.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanRecordDto {
    private Long id;

    private String note;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends BookLoanRecordDto {
        private Long libraianId;

        private String librarianName;

        private Long studentId;

        private String studentName;

        private String location;

        private LocalDateTime loanDate;

        private Collection<BookLoanRecordItemDto.Response> books;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetailResponse extends BookLoanRecordDto {
        private LibrarianDto.CommonField librarian;

        private StudentDto.CommonField student;

        private LocationDto location;

        private LocalDateTime loanDate;

        private Collection<BookLoanRecordItemDto.Response> books;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends BookLoanRecordDto {
        private Long librarian;

        private Long student;

        private Collection<BookLoanRecordItemDto.Request> books;
    }
}