package com.kynsof.report.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Report Services")
                .description("API Documentation Report Services")
                .version("1.0.0");
                /*.license(new License()
                        .name(openApiProperties.getLicense())
                        .url(openApiProperties.getLicenseUrl()))
                .termsOfService(openApiProperties.getTermsOfService())
                .contact(openApiProperties.getContact())
                .extensions(openApiProperties.getExtensions());*/
    }
}