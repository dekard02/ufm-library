package com.ufm.library.dto;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufm.library.entity.Book;
import com.ufm.library.entity.Location;
import com.ufm.library.validation.annotation.Exist;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class LocationBookDto {

    @Exist(entityClass = Location.class, message = "Không tồn tại cơ sở với mã {}")
    private Long locationId;

    @Exist(entityClass = Book.class, message = "Không tồn tại sách với mã {}")
    private Long bookId;

    @Size(min = 0, message = "Số lượng sách không thể bé hơn 0")
    private Integer quantity;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends LocationBookDto {
        private Integer booksOnLoan;
        private String locationName;
        private String address;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetailResponse extends LocationBookDto {
        private Integer booksOnLoan;
        private LocationDto location;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateRequest extends LocationBookDto {

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UpdateRequest extends LocationBookDto {

        @Size(min = 0, message = "Số lượng sách đang mượn không thể bé hơn 0")
        private Integer booksOnLoan;
    }
}
