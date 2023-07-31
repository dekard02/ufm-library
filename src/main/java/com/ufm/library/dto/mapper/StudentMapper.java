package com.ufm.library.dto.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ufm.library.dto.StudentDto;
import com.ufm.library.entity.Student;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface StudentMapper {

    StudentDto.Response studentToStudentResDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "bookLoanRecords", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Student studentReqDtoToStudent(StudentDto.Request studentDto);

    StudentDto.CommonField studentToStudentCommonFieldDto(Student student);
}
