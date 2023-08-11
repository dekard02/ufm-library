package com.ufm.library.controller.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.ufm.library.controller.StudentController;
import com.ufm.library.dto.StudentDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.Student;
import com.ufm.library.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/students")
@PreAuthorize("hasAnyRole('LIBRARIAN','MANAGER')")
@RequiredArgsConstructor
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllStudents(
            @QuerydslPredicate(root = Student.class) Predicate predicate,
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "", required = false) String search) {
        var response = studentService.getAllStudents(predicate, pageable, search);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ResponseBody> getStudent(@PathVariable String id) {
        var response = studentService.getStudent(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createStudent(StudentDto.Request studentDto) {
        var response = studentService.saveStudent(studentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ResponseBody> updateStudent(@PathVariable String id,
            StudentDto.Request studentDto) {
        var response = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBody> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
