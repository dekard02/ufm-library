package com.ufm.library.config;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ufm.library.entity.Location;
import com.ufm.library.entity.Role;
import com.ufm.library.repository.LocationRepository;
import com.ufm.library.repository.RoleRepository;

@Order(0)
@Component
public class InitDataConfig implements CommandLineRunner {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
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
                    .roleCode("ROLE_" + role.name())
                    .roleName(role.toString())
                    .build();
            roleRepository.save(roleEntity);
        });
    }
}
