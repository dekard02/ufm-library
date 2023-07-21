package com.ufm.library.faker;

import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.constant.StorageContants;
import com.ufm.library.entity.Student;
import com.ufm.library.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentFaker {

    private final Faker faker;
    private final ProfileFaker profileFaker;
    private final StudentRepository studentRepository;

    public void fake() {
        for (int i = 0; i < 25; i++) {
            var profile = profileFaker.fake();
            String id = faker.regexify("(2020|2021|2022)01(\\d{4})");
            var dateOfBirth = faker
                    .date().birthday(18, 25)
                    .toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            // password : password
            var password = "$2a$12$Cug38IrpgoVxKcS/tA6hWuS786KfEfBAa6QgrQsiaCAiryGDpsm8W";

            var student = Student.builder()
                    .id(id)
                    .fullname(profile.getFullname())
                    .address(profile.getAddress())
                    .phoneNumber(profile.getPhoneNumber())
                    .password(password)
                    .photo(StorageContants.DEFAULT_AVARTA)
                    .dateOfBirth(dateOfBirth)
                    .gender(profile.getGender())
                    .build();
            studentRepository.save(student);
        }
    }
}
