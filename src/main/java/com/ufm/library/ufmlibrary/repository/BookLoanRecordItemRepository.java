package com.ufm.library.ufmlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.ufmlibrary.entity.BookLoanRecordItem;
import com.ufm.library.ufmlibrary.entity.key.BookLoanRecordItemKey;

public interface BookLoanRecordItemRepository
        extends JpaRepository<BookLoanRecordItem, BookLoanRecordItemKey> {

}
