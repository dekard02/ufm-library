package com.ufm.library.ufmlibrary.faker;

import org.springframework.stereotype.Service;

import com.ufm.library.ufmlibrary.entity.Author;
import com.ufm.library.ufmlibrary.entity.Location;
import com.ufm.library.ufmlibrary.entity.Role;
import com.ufm.library.ufmlibrary.repository.AuthorRepository;
import com.ufm.library.ufmlibrary.repository.LocationRepository;
import com.ufm.library.ufmlibrary.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataFaker {

    private final AuthorRepository authorRepository;
    private final LocationRepository locationRepository;
    private final RoleRepository roleRepository;
    private final BookFaker bookFaker;
    private final StudentFaker studentFaker;
    private final LibrarianFaker librarianFaker;
    private final RecordFaker recordFaker;

    public void fake() {
        fakeAuthor();
        fakeLocation();
        bookFaker.fake();
        fakeRole();
        studentFaker.fake();
        librarianFaker.fake();
        recordFaker.fake();
    }

    private void fakeRole() {
        roleRepository.save(new Role(null, Role.RoleName.MANAGER, null));
        roleRepository.save(new Role(null, Role.RoleName.LIBRARIAN, null));
    }

    private void fakeAuthor() {
        authorRepository.save(new Author(null, "Yuval Noah Harari", null, null)); // 1
        authorRepository.save(new Author(null, "Dale Carnegie", null, null));// 2
        authorRepository.save(new Author(null, "Paulo Coelho", null, null));// 3
        authorRepository.save(new Author(null, "Sasaki Fumio", null, null));// 4
        authorRepository.save(new Author(null, "Napoleon Hill", null, null));// 5
        authorRepository.save(new Author(null, "Steven Covey", null, null));// 6
        authorRepository.save(new Author(null, "Nick Vujic", null, null));// 7
        authorRepository.save(new Author(null, "Rosie Nguyễn", null, null));// 8
        authorRepository.save(new Author(null, "Nguyễn Nhật Ánh", null, null));// 9
    }

    private void fakeLocation() {
        locationRepository.save(new Location(null, "Địa chỉ 1",
                "Lầu 7, Khu B, số 27 đường Tân Mỹ, phường Tân Thuận Tây, Quận 7, TP HCM", null));
        locationRepository.save(new Location(null, "Địa chỉ 2",
                "Phòng B004 & B003 cơ sở 778 Nguyễn Kiệm, P.4, Q. Phú Nhuận, TP Hồ Chí Minh", null));
        locationRepository.save(new Location(null, "Địa chỉ 3",
                "B2/1A đường 385, phường Tăng Nhơn Phú A, Quận 9, Thành phố Thủ Đức ", null));
    }

}
