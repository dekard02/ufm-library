package com.ufm.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ufm.library.faker.DataFaker;

@Order(1)
@Component
public class FakeDataConfig implements CommandLineRunner {

    @Autowired
    private DataFaker dataFaker;

    @Override
    public void run(String... args) throws Exception {
        dataFaker.fake();
    }
}
