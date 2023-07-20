package com.ufm.library.ufmlibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ufm.library.ufmlibrary.faker.DataFaker;

@SpringBootApplication
public class UfmLibraryApplication {
	@Autowired
	private DataFaker dataFaker;

	public static void main(String[] args) {
		SpringApplication.run(UfmLibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> dataFaker.fake();
		// return args -> {};
	}

}
