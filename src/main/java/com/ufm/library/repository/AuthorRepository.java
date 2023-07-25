package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>,
        QuerydslPredicateExecutor<Author> {
}
