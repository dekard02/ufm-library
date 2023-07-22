package com.ufm.library.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookReturnRecordDto {
    private Long id;

    private LocalDateTime returnDate;

    private Long bookLoanRecordId;

    private LibrarianDto.CommonField librarian;

    private Double fine;

    private String note;
}
