package com.ufm.library.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private Long id;

    private String title;

    private String slug;

    private Integer totalPages;

    private String summary;

    private Boolean isDeleted;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response extends BookDto {
        private AuthorDto author;
        private List<String> photos;
        private List<CategoryDto> categories;
        private LocalDateTime createdAt;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Detail extends BookDto {
        private AuthorDto author;
        private List<String> photos;
        private List<CategoryDto> categories;
        private LocalDateTime createdAt;
        private List<LocationBookDto.Response> locationBooks;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateRequest extends BookDto {
        private Long author;
        // not null
        private List<MultipartFile> photos;
        private List<Long> categories;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UpdateRequest extends BookDto {
        private Long author;
        private List<MultipartFile> photos;
        private List<Long> categories;
    }

}
