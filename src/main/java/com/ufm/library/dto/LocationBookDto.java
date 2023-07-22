package com.ufm.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class LocationBookDto {
    private Long locationId;

    private Long bookId;

    private Integer quantity;

    private Integer booksOnLoan;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends LocationBookDto {
        private String location;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetailResponse extends LocationBookDto {
        private LocationDto location;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request extends LocationBookDto {

    }
}
