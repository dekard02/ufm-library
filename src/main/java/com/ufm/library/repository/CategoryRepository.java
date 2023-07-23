package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
