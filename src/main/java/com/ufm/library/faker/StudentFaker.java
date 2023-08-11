package com.ufm.library.faker;

import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.ufm.library.constant.Constants;
import com.ufm.library.constant.StorageContants;
import com.ufm.library.entity.Student;
import com.ufm.library.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentFaker {

    private static final List<String> STUDENT_ADDRESS = List.of(
            "Phường Tam Bình, Quận Thủ Đức, Hồ Chí Minh (tphcm)",
            "Xã Triệu ẩu, Huyện Phục Hoà, Cao Bằng",
            "Xã Liên Sơn, Huyện Lương Sơn, Hoà Bình",
            "Phường Phương Liệt, Quận Thanh Xuân, Hà Nội",
            "Xã Phú Thượng, Huyện Võ Nhai, Thái Nguyên",
            "Xã Tống Trân, Huyện Phù Cừ, Hưng Yên",
            "Phường Hòa Minh, Quận Liên Chiểu, Đà Nẵng",
            "Xã Phúc Lộc, Huyện Ba Bể, Bắc cạn",
            "Xã Tân An, Huyện Đăk Pơ, Gia Lai",
            "Xã Hồng Dụ, Huyện Ninh Giang, Hải Dương",
            "Xã Pá Lau, Huyện Trạm Tấu, Yên Bái",
            "Xã Đồng Môn, Huyện Lạc Thủy, Hoà Bình",
            "Xã Đức Liên, Huyện Vũ Quang, Hà Tĩnh",
            "Thị trấn Vương, Huyện Tiên Lữ, Hưng Yên",
            "Xã Định Thành, Huyện Dầu Tiếng, Bình Dương",
            "Xã Kim Nọi, Huyện Mù Căng Chải, Yên Bái",
            "Xã Vĩnh Mỹ B, Huyện Hoà Bình, Bạc Liêu",
            "Xã Hoài Hảo, Huyện Hoài Nhơn, Bình Định",
            "Xã Châu Phong, Huyện Quỳ Châu, Nghệ An",
            "Xã Ninh Quới, Huyện Hồng Dân, Bạc Liêu",
            "Phường Tào Xuyên, Thành phố Thanh Hóa, Thanh Hóa",
            "Xã Long Bình, Huyện Gò Công Tây, Tiền Giang",
            "Xã Tân Lập, Huyện Tân Biên, Tây Ninh",
            "Xã Ea Kiết, Huyện Cư M'gar, Đắc Lắc",
            "Thị trấn Tằng Loỏng, Huyện Bảo Thắng, Lào Cai");

    private final Faker faker;
    private final StudentRepository studentRepository;

    public void fake() {
        var first = Student.builder()
                .id("2021010175")
                .fullname("Nguyễn Đăng Khoa")
                .address("ấp 11, Tân Hào, Giống Trôm, Bến Tre")
                .phoneNumber(faker.phoneNumber().cellPhone())
                .password(Constants.ENCODED_DEFAULT_PASSWORD)
                .photo(StorageContants.STUDENT_DEFAULT_IMAGE)
                .gender(faker.bool().bool())
                .build();
        studentRepository.save(first);

        for (int i = 0; i < 25; i++) {
            String id = faker.regexify("(2020|2021|2022)010(\\d{3})");
            var dateOfBirth = faker
                    .date().birthday(18, 25)
                    .toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            var student = Student.builder()
                    .id(id)
                    .fullname(faker.name().nameWithMiddle())
                    .address(STUDENT_ADDRESS.get(i))
                    .phoneNumber(faker.phoneNumber().cellPhone())
                    .password(Constants.ENCODED_DEFAULT_PASSWORD)
                    .photo(StorageContants.STUDENT_DEFAULT_IMAGE)
                    .dateOfBirth(dateOfBirth)
                    .gender(faker.bool().bool())
                    .build();
            studentRepository.save(student);
        }
    }
}
