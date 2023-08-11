package com.ufm.library.faker;

import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import com.ufm.library.constant.Constants;
import com.ufm.library.constant.StorageContants;
import com.ufm.library.entity.Librarian;
import com.ufm.library.repository.LibrarianRepository;
import com.ufm.library.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibrarianFaker {
    private final Faker faker;
    private final LibrarianRepository librarianRepository;
    private final RoleRepository roleRepository;
    private final Slugify slugify;

    public void fake() {
        var admin = Librarian.builder()
                .fullname("Administrator")
                .username("admin")
                .password(Constants.ENCODED_DEFAULT_PASSWORD)
                .citizenId("123123123123")
                .photo(StorageContants.LIBRARIAN_DEFAULT_IMAGE)
                .phoneNumber(faker.phoneNumber().cellPhone())
                .role(roleRepository.getReferenceById(1L))
                .build();
        librarianRepository.save(admin);

        for (int i = 0; i < 10; i++) {
            var citezenId = faker.regexify("\\d{10}");
            var dateOfBirth = faker
                    .date().birthday(20, 35)
                    .toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            var fullname = faker.name().nameWithMiddle();
            var username = String.join(".", slugify.slugify(fullname).split("-")) + "."
                    + String.format("%02d", dateOfBirth.getDayOfMonth())
                    + String.format("%02d", dateOfBirth.getMonthValue());

            var role = roleRepository.getReferenceById(2L);

            if (i < 3) {
                role = roleRepository.getReferenceById(1L);
            }

            var librarian = Librarian.builder()
                    .fullname(fullname)
                    .username(username)
                    .password(Constants.ENCODED_DEFAULT_PASSWORD)
                    .citizenId(citezenId)
                    .photo(StorageContants.LIBRARIAN_DEFAULT_IMAGE)
                    .dateOfBirth(dateOfBirth)
                    .phoneNumber(faker.phoneNumber().cellPhone())
                    .role(role)
                    .build();
            librarianRepository.save(librarian);
        }
    }
}
