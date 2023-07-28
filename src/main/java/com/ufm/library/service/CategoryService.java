package com.ufm.library.service;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.CategoryDto;
import com.ufm.library.dto.api.ResponseBody;

public interface CategoryService {
    public ResponseBody getAllCategories(Predicate predicate, Pageable pageable, String search);

    public ResponseBody getCategory(Long id);

    public ResponseBody saveCategory(CategoryDto categoryDto);

    public ResponseBody updateCategory(Long id, CategoryDto categoryDto);

    public void deleteCategory(Long id);
}