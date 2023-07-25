package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.BookReturnRecord;

public interface BookReturnRecordRepository extends JpaRepository<BookReturnRecord, Long>,
        QuerydslPredicateExecutor<BookReturnRecord> {

}
