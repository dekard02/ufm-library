package com.ufm.library.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.StudentDto;
import com.ufm.library.dto.api.ResponseBody;

public interface StudentService {
    public ResponseBody getAllStudents(Predicate predicate, Pageable pageable, String search);

    public ResponseBody getStudent(String id);

    public ResponseBody saveStudent(@Valid StudentDto.Request studentDto);

    public ResponseBody updateStudent(String id, @Valid StudentDto.Request studentDto);

    public void deleteStudent(String id);
}
