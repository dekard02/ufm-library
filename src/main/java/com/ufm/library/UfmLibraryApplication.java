package com.ufm.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ufm.library.entity.Location;
import com.ufm.library.entity.Role;
import com.ufm.library.faker.DataFaker;
import com.ufm.library.repository.LocationRepository;
import com.ufm.library.repository.RoleRepository;

@SpringBootApplication
public class UfmLibraryApplication {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private DataFaker dataFaker;

	public static void main(String[] args) {
		SpringApplication.run(UfmLibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			// boot data
			locationRepository.save(Location.builder()
					.name("Địa chỉ 1")
					.address("Lầu 7, Khu B, số 27 đường Tân Mỹ, phường Tân Thuận Tây, Quận 7, TP HCM")
					.build());

			locationRepository.save(Location.builder()
					.name("Địa chỉ 2")
					.address("Phòng B004 & B003 cơ sở 778 Nguyễn Kiệm, P.4, Q. Phú Nhuận, TP Hồ Chí Minh")
					.build());

			locationRepository.save(Location.builder()
					.name("Địa chỉ 3")
					.address("B2/1A đường 385, phường Tăng Nhơn Phú A, Quận 9, Thành phố Thủ Đức")
					.build());

			roleRepository.save(Role.builder().role(Role.RoleName.MANAGER).build());
			roleRepository.save(Role.builder().role(Role.RoleName.LIBRARIAN).build());

			// fake data
			dataFaker.fake();
		};
		// return args -> {};
	}

}
