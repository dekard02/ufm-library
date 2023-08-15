package com.ufm.library.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufm.library.entity.Book;
import com.ufm.library.entity.Location;
import com.ufm.library.validation.annotation.Exist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanRecordItemDto {

    @NotNull(message = "Trường books[].quantity không được bỏ trống")
    private Integer quantity;

    private String note;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends BookLoanRecordItemDto {
        private Long bookId;
        private String bookTitle;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends BookLoanRecordItemDto {

        @NotNull(message = "Trường books[].location không được bỏ trống")
        @Exist(entityClass = Location.class, message = "Không tồn tại cơ sở với mã {}")
        private Long location;

        @NotNull(message = "Trường books[].book không được bỏ trống")
        @Exist(entityClass = Book.class, message = "Không tồn tại sách với mã {}")
        private Long book;
    }
}
