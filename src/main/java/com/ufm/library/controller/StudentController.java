package com.ufm.library.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.StudentDto;
import com.ufm.library.dto.api.ResponseBody;

public interface StudentController {
    public ResponseEntity<ResponseBody> getAllStudents(Predicate predicate, Pageable pageable, String search);

    public ResponseEntity<ResponseBody> getStudent(String id);

    public ResponseEntity<ResponseBody> createStudent(StudentDto.Request studentDto);

    public ResponseEntity<ResponseBody> updateStudent(String id, StudentDto.Request studentDto);

    public ResponseEntity<ResponseBody> deleteStudent(String id);
}
