package com.javaupgradation.javacrudoperation.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomiser customOpenApiCustomiser() {
        return new OpenApiCustomiser() {
            @Override
            public void customise(io.swagger.v3.oas.models.OpenAPI openApi) {
                openApi.getInfo()
                        .title("CRUD Operations API")
                        .description("This API allows performing CRUD operations on users. "
                                + "Endpoints include operations like create, read, update, and delete users.")
                        .version("v1.0.0");
            }
        };
    }
}
