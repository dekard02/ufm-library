package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.LocationBook;

public interface LocationBookRepository extends JpaRepository<LocationBook, Long>,
        QuerydslPredicateExecutor<LocationBook> {

}
