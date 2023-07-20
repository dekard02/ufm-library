package com.ufm.library.ufmlibrary.faker;

import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.ufmlibrary.entity.Librarian;
import com.ufm.library.ufmlibrary.repository.LibrarianRepository;
import com.ufm.library.ufmlibrary.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibrarianFaker {
    private final Faker faker;
    private final ProfileFaker profileFaker;
    private final LibrarianRepository librarianRepository;
    private final RoleRepository roleRepository;

    public void fake() {
        for (int i = 0; i < 10; i++) {
            var profile = profileFaker.fake();
            var citezenId = faker.regexify("\\d{10}");
            var dateOfBirth = faker
                    .date().birthday(20, 35)
                    .toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            var password = "$2a$12$BvDLLqd4IWgfSVI0fkzYOOgC6zkdVQc.IfTTVlYayrf0.KIPBq3eK";
            var role = roleRepository.getReferenceById(2L);

            if (i < 3) {
                role = roleRepository.getReferenceById(1L);
            }

            var librarian = new Librarian(null,
                    profile.getFullname(),
                    profile.getUsername(),
                    password,
                    citezenId,
                    "",
                    dateOfBirth,
                    profile.getPhoneNumber(),
                    role,
                    null, null, null);
            librarianRepository.save(librarian);
        }
    }
}
