package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.LocationBook;

public interface LocationBookRepository extends JpaRepository<LocationBook, Long> {

}
