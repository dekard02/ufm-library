package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.CategoryDto;
import com.ufm.library.dto.api.ResponseBody;

public interface CategoryController {
    public ResponseEntity<ResponseBody> getAllCategories(Predicate predicate, Pageable pageable, String search);

    public ResponseEntity<ResponseBody> getCategory(Long id);

    public ResponseEntity<ResponseBody> createCategory(CategoryDto categoryDto);

    public ResponseEntity<ResponseBody> updateCategory(Long id, CategoryDto categoryDto);

    public ResponseEntity<ResponseBody> deleteCategory(Long id);
}
