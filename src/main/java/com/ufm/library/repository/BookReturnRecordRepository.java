package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.BookReturnRecord;

public interface BookReturnRecordRepository extends JpaRepository<BookReturnRecord, Long> {

}
