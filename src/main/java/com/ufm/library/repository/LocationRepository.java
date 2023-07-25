package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long>,
        QuerydslPredicateExecutor<Location> {

}
