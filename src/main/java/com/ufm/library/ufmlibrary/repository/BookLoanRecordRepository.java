package com.ufm.library.ufmlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.ufmlibrary.entity.BookLoanRecord;

public interface BookLoanRecordRepository extends JpaRepository<BookLoanRecord, Long> {

}