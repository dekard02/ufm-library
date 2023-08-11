package com.ufm.library.dto;

import java.time.LocalDateTime;
import java.util.List;

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
    public static class Response extends BookLoanRecordDto {
        private Long librarianId;

        private String librarianName;

        private String studentId;

        private String studentName;

        private String address;

        private String locationName;

        private LocalDateTime loanDate;

        private List<BookLoanRecordItemDto.Response> books;

        private BookReturnRecordDto.InLoanRecord bookReturnRecord;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DetailResponse extends BookLoanRecordDto {
        private LibrarianDto.CommonField librarian;

        private StudentDto.CommonField student;

        private String locationName;

        private String address;

        private LocalDateTime loanDate;

        private List<BookLoanRecordItemDto.Response> books;

        private BookReturnRecordDto.InLoanRecord bookReturnRecord;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Request extends BookLoanRecordDto {
        private String student;

        private List<BookLoanRecordItemDto.Request> books;
    }
}