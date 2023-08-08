package com.ufm.library.dto.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ufm.library.dto.StudentDto;
import com.ufm.library.entity.Student;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface StudentMapper {

    @Mapping(target = "photo", source = "student", qualifiedByName = "photo")
    StudentDto.Response studentToStudentResDto(Student student);

    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Student studentReqDtoToStudent(StudentDto.Request studentDto);

    StudentDto.CommonField studentToStudentCommonFieldDto(Student student);

    @Named("photo")
    default String photo(Student librarian) {
        var rootUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return rootUrl + "/" + librarian.getPhoto();
    }
}
