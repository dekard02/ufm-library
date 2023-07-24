package com.ufm.library;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;

@Configuration
@EnableJpaAuditing
@EnableSpringDataWebSupport
public class ApplicationConfig {
    @Bean
    public Faker faker() {
        return new Faker(new Locale("vi"));
    }

    @Bean
    public Slugify slugify() {
        return Slugify.builder()
                .locale(new Locale("vi"))
                .build();
    }

}
