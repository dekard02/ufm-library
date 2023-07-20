package com.ufm.library.ufmlibrary.faker;

import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.ufmlibrary.entity.Student;
import com.ufm.library.ufmlibrary.repository.StudentRepository;

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
            var password = "$2a$12$BvDLLqd4IWgfSVI0fkzYOOgC6zkdVQc.IfTTVlYayrf0.KIPBq3eK";

            var student = new Student(id,
                    profile.getFullname(),
                    profile.getAddress(),
                    profile.getPhoneNumber(),
                    password,
                    "",
                    dateOfBirth,
                    profile.getGender(),
                    null, null);
            studentRepository.save(student);
        }
    }
}
