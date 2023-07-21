package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.BookLoanRecordItem;
import com.ufm.library.entity.key.BookLoanRecordItemKey;

public interface BookLoanRecordItemRepository
                extends JpaRepository<BookLoanRecordItem, BookLoanRecordItemKey> {

}
