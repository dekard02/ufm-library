package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.BookLoanRecord;

public interface BookLoanRecordRepository extends JpaRepository<BookLoanRecord, Long> {

}