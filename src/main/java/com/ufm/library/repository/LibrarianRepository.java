package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

}
