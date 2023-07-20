package com.ufm.library.ufmlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.ufmlibrary.entity.BookReturnRecord;

public interface BookReturnRecordRepository extends JpaRepository<BookReturnRecord, Long> {

}
