package com.ufm.library.faker;

import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.constant.StorageContants;
import com.ufm.library.entity.Librarian;
import com.ufm.library.repository.LibrarianRepository;
import com.ufm.library.repository.RoleRepository;

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
            // password : password
            var password = "$2a$12$Cug38IrpgoVxKcS/tA6hWuS786KfEfBAa6QgrQsiaCAiryGDpsm8W";
            var role = roleRepository.getReferenceById(2L);

            if (i < 3) {
                role = roleRepository.getReferenceById(1L);
            }

            var librarian = Librarian.builder()
                    .fullname(profile.getFullname())
                    .username(profile.getUsername())
                    .password(password)
                    .citizenId(citezenId)
                    .photo(StorageContants.DEFAULT_AVARTA)
                    .dateOfBirth(dateOfBirth)
                    .phoneNumber(profile.getPhoneNumber())
                    .role(role)
                    .build();
            librarianRepository.save(librarian);
        }
    }
}
