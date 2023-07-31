package com.ufm.library.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;

@Configuration
@EnableJpaAuditing
@EnableSpringDataWebSupport
public class ApplicationConfig {

    // spring.data.web.pageable.one-indexed-parameters=true dosent work
    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
        return pageableResolver -> pageableResolver.setOneIndexedParameters(true);
    }

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
