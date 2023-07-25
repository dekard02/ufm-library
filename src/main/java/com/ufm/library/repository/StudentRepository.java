package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String>,
        QuerydslPredicateExecutor<Student> {

}
