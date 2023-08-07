package com.ufm.library.config;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ufm.library.entity.Location;
import com.ufm.library.entity.Role;
import com.ufm.library.faker.DataFaker;
import com.ufm.library.repository.LocationRepository;
import com.ufm.library.repository.RoleRepository;

@Configuration
public class FakeDataConfig {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DataFaker dataFaker;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // boot data
            locationRepository.save(Location.builder()
                    .name("Địa chỉ 1")
                    .phoneNumber("(028)37720403")
                    .address("Lầu 7, Khu B, số 27 đường Tân Mỹ, phường Tân Thuận Tây, Quận 7, TP HCM")
                    .build());

            locationRepository.save(Location.builder()
                    .name("Địa chỉ 2")
                    .phoneNumber("(028)39976462")
                    .address("Phòng B004 & B003 cơ sở 778 Nguyễn Kiệm, P.4, Q. Phú Nhuận, TP Hồ Chí Minh")
                    .build());

            locationRepository.save(Location.builder()
                    .name("Địa chỉ 3")
                    .phoneNumber("(028)37367095")
                    .address("B2/1A đường 385, phường Tăng Nhơn Phú A, Quận 9, Thành phố Thủ Đức")
                    .build());

            Stream.of(Role.RoleEnum.values()).forEach((role) -> {
                var roleEntity = Role.builder()
                        .roleCode(role.name())
                        .roleName(role.toString())
                        .build();
                roleRepository.save(roleEntity);
            });

            // fake data
            dataFaker.fake();
        };
        // return args -> {};
    }
}