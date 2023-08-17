package com.ufm.library.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ufm.library.entity.Author;
import com.ufm.library.entity.Category;
import com.ufm.library.validation.annotation.Exist;
import com.ufm.library.validation.annotation.IsImage;
import com.ufm.library.validation.annotation.ListSize;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    @Hidden
    private Long id;

    @NotBlank(message = "Trường fullname không được bỏ trống")
    @Size(max = 150, message = "Tiêu đề sách không được dài quá 150 ký tự")
    private String title;

    @Hidden
    private String slug;

    private Integer totalPages;

    @NotBlank(message = "Trường summary không được bỏ trống")
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
    @Schema(name = "Book")
    public static class CreateRequest extends BookDto {

        @NotNull(message = "Trường author không được bỏ trống")
        @Exist(entityClass = Author.class, message = "Không tồn tại tác giả với mã {}")
        private Long author;

        @ListSize(min = 3, max = 5, message = "Trường photos phải tối thiểu 3 ảnh và tối đa 5 ảnh")
        @NotNull(message = "Trường photos không được bỏ trống")
        private List<@IsImage MultipartFile> photos;

        @NotNull(message = "Trường categories không được bỏ trống")
        @ListSize(min = 1, message = "Phải có tối thiểu một loại sách")
        private List<@Exist(entityClass = Category.class, message = "Không tồn tại loại sách với mã {}") Long> categories;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UpdateRequest extends BookDto {

        @Exist(entityClass = Author.class, message = "Không tồn tại tác giả với mã {}")
        @NotNull(message = "Trường author tên không được bỏ trống")
        private Long author;

        private List<@IsImage MultipartFile> photos;

        @NotNull(message = "Trường categories tên không được bỏ trống")
        private List<@Exist(entityClass = Category.class, message = "Không tồn tại loại sách với mã {}") Long> categories;
    }

}
