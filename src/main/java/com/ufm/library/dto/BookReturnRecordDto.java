package com.ufm.library.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufm.library.entity.BookLoanRecord;
import com.ufm.library.validation.annotation.Exist;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BookReturnRecordDto {

    @Hidden
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
    @Schema(name = "BookReturnRecord")
    public static class DetailResponse extends BookReturnRecordDto {
        private LibrarianDto.CommonField librarian;

        private Long bookLoanRecordId;

        private LocalDateTime returnDate;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends BookReturnRecordDto {

        @NotNull(message = "Trường bookLoanRecord không được bỏ trống")
        @Exist(entityClass = BookLoanRecord.class, message = "Không tồn tại phiếu mượn với mã {}")
        private Long bookLoanRecord;
    }
}
