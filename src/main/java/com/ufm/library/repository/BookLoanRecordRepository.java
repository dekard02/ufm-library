package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.BookLoanRecord;

public interface BookLoanRecordRepository extends JpaRepository<BookLoanRecord, Long>,
        QuerydslPredicateExecutor<BookLoanRecord> {

}