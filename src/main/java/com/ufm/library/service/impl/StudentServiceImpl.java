package com.ufm.library.service.impl;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.Predicate;
import com.ufm.library.constant.Constants;
import com.ufm.library.constant.StorageContants;
import com.ufm.library.dto.StudentDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.StudentMapper;
import com.ufm.library.entity.QStudent;
import com.ufm.library.entity.Student;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.FilenameHelper;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.StudentRepository;
import com.ufm.library.service.StorageService;
import com.ufm.library.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final StudentMapper studentMapper;
    private final StorageService storageService;
    private final ResponseBodyHelper responseBodyHelper;
    private final FilenameHelper filenameHelper;

    @Override
    public ResponseBody getAllStudents(Predicate predicate, Pageable pageable, String search) {
        var searchPredicate = QStudent.student.fullname
                .concat(QStudent.student.phoneNumber)
                .concat(QStudent.student.address)
                .containsIgnoreCase(search)
                .and(predicate);
        var studentPage = studentRepo.findAll(searchPredicate, pageable);
        var students = studentPage.getContent()
                .stream()
                .map(studentMapper::studentToStudentResDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(studentPage, "students", students);
    }

    @Override
    public ResponseBody getStudent(String id) {
        var studentDto = studentRepo.findById(id)
                .map(studentMapper::studentToStudentResDto)
                .orElseThrow(() -> new ApplicationException(
                        "Không tìm thấy sinh viên với mã " + id, HttpStatus.NOT_FOUND));
        return responseBodyHelper.success("student", studentDto);
    }

    private void saveImage(Student student, MultipartFile multipartFile) {
        var photo = StorageContants.STUDENT_DEFAULT_IMAGE;
        try {
            var extension = filenameHelper.getMultipartFileExtension(multipartFile);
            var photoName = UUID.randomUUID().toString() + "." + extension;
            var inputStream = multipartFile.getInputStream();
            photo = storageService.store(inputStream, photoName,
                    StorageContants.STUDENT_IMAGES_FOLDER);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        student.setPhoto(photo);
    }

    @Override
    public ResponseBody saveStudent(StudentDto.Request studentDto) {
        var student = studentMapper.studentReqDtoToStudent(studentDto);

        student.setPassword(Constants.ENCODED_DEFAULT_PASSWORD);

        if (studentDto.getPhoto() != null) {
            saveImage(student, studentDto.getPhoto());
        } else {
            student.setPhoto(StorageContants.STUDENT_DEFAULT_IMAGE);
        }

        studentRepo.save(student);
        return responseBodyHelper
                .success("student", studentMapper.studentToStudentResDto(student));
    }

    @Override
    public ResponseBody updateStudent(String id, StudentDto.Request studentDto) {
        var student = studentRepo
                .findById(id)
                .orElseThrow(
                        () -> new ApplicationException("Không tìm thấy loại sách với mã " + id,
                                HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(studentDto, student, "password", "photo", "createdAt");
        student.setId(id);

        if (studentDto.getPhoto() != null) {
            var oldPhoto = student.getPhoto();
            saveImage(student, studentDto.getPhoto());
            if (oldPhoto != StorageContants.STUDENT_DEFAULT_IMAGE) {
                storageService.delete(oldPhoto);
            }
        }

        studentRepo.save(student);
        return responseBodyHelper
                .success("student", studentMapper.studentToStudentResDto(student));
    }

    @Override
    public void deleteStudent(String id) {
        studentRepo.findById(id).ifPresentOrElse(
                (student) -> {
                    student.setIsDeleted(true);
                    studentRepo.save(student);
                },
                () -> {
                    throw new ApplicationException("Không tìm thấy sinh viên với mã " + id,
                            HttpStatus.NOT_FOUND);
                });
    }
}
