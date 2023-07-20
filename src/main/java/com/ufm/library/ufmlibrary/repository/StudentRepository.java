package com.ufm.library.ufmlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.ufmlibrary.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
