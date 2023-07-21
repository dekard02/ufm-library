package com.ufm.library;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.github.javafaker.Faker;

@Configuration
@EnableJpaAuditing
public class ApplicationConfig {
    @Bean
    public Faker faker() {
        return new Faker(new Locale("vi"));
    }

}
