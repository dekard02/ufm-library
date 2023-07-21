package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
