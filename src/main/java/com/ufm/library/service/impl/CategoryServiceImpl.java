package com.ufm.library.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.CategoryDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.CategoryMapper;
import com.ufm.library.entity.QCategory;
import com.ufm.library.exception.NotFoundException;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.CategoryRepository;
import com.ufm.library.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;
    private final ResponseBodyHelper responseBodyHelper;

    @Override
    public ResponseBody getAllCategories(Predicate predicate, Pageable pageable, String search) {
        var searchPredicate = QCategory.category.name
                .contains(search)
                .and(predicate);
        var categoryPage = categoryRepo.findAll(searchPredicate, pageable);
        var categories = categoryPage.getContent()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(categoryPage, "categories", categories);
    }

    @Override
    public ResponseBody getCategory(Long id) {
        var categoryDto = categoryRepo.findById(id)
                .map(categoryMapper::categoryToCategoryDto)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy loại sách với mã " + id));
        return responseBodyHelper.success("category", categoryDto);
    }

    @Override
    public ResponseBody saveCategory(CategoryDto categoryDto) {
        var category = categoryMapper.categoryDtoToCategory(categoryDto);
        category.setId(null);
        categoryRepo.save(category);
        return responseBodyHelper
                .success("category", categoryMapper.categoryToCategoryDto(category));
    }

    @Override
    public ResponseBody updateCategory(Long id, CategoryDto categoryDto) {
        if (!categoryRepo.existsById(id)) {
            throw new NotFoundException("Không tìm thấy loại sách với mã " + id);
        }
        var category = categoryMapper.categoryDtoToCategory(categoryDto);
        category.setId(id);
        categoryRepo.save(category);
        return responseBodyHelper
                .success("category", categoryMapper.categoryToCategoryDto(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.findById(id).ifPresentOrElse(
                (category) -> {
                    category.setIsDeleted(true);
                    categoryRepo.save(category);
                },
                () -> {
                    throw new NotFoundException("Không tìm thấy loại sách với mã " + id);
                });
    }
}
