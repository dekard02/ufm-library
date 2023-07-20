package com.ufm.library.ufmlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.ufmlibrary.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
