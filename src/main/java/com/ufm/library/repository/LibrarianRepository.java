package com.ufm.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Long>,
        QuerydslPredicateExecutor<Librarian> {
    public Optional<Librarian> findByUsername(String username);
}
