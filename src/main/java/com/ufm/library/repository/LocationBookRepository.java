package com.ufm.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.LocationBook;
import com.ufm.library.entity.key.LocationBookKey;

public interface LocationBookRepository extends JpaRepository<LocationBook, LocationBookKey>,
                QuerydslPredicateExecutor<LocationBook> {
        public List<LocationBook> findByBookId(Long bookId);
}
