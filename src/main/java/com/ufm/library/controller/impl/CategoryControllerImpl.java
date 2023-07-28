package com.ufm.library.controller.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.ufm.library.controller.CategoryController;
import com.ufm.library.dto.CategoryDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.Category;
import com.ufm.library.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllCategories(
            @QuerydslPredicate(root = Category.class) Predicate predicate,
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "", required = false) String search) {
        var response = categoryService.getAllCategories(predicate, pageable, search);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ResponseBody> getCategory(@PathVariable Long id) {
        var response = categoryService.getCategory(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createCategory(@RequestBody CategoryDto categoryDto) {
        var response = categoryService.saveCategory(categoryDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ResponseBody> updateCategory(@PathVariable Long id,
            @RequestBody CategoryDto categoryDto) {
        var response = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBody> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
