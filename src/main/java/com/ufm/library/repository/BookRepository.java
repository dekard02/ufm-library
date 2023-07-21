package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
