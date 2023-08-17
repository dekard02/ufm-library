package com.ufm.library.config;

import java.util.List;

import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Value("${open-api.app-version}")
    private String appVersion;

    @Value("${open-api.production-server-url}")
    private String productionServerUrl;

    static {
        SpringDocUtils.getConfig()
                .replaceParameterObjectWithClass(org.springframework.data.domain.Pageable.class,
                        Pageable.class)
                .replaceParameterObjectWithClass(org.springframework.data.domain.PageRequest.class,
                        Pageable.class);
    }

    @Bean
    public OpenAPI openAPI() {
        var info = new Info()
                .title("UFM Library Management API")
                .version("1.0")
                .contact(new Contact()
                        .email("ngdangkhoa02@gmail.com")
                        .name("Nguyễn Đăng Khoa")
                        .url("https://github.com/dekard02"));

        var localServer = new Server().url("http://localhost:8080");
        var productionServer = new Server().url(productionServerUrl);

        var security = new Components().addSecuritySchemes(
                "JWT",
                new SecurityScheme()
                        .name("JWT")
                        .in(In.HEADER)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer"));

        return new OpenAPI()
                .components(security)
                .servers(List.of(localServer, productionServer))
                .info(info);
    }
}
