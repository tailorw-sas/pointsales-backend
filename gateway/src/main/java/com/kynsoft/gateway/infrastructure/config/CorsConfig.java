package com.kynsoft.gateway.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

    @Setter
    @Getter
    @Configuration
    @ConfigurationProperties(prefix = "https")
    public class CorsConfig {
        private CorsConfiguration cors = new CorsConfiguration();

        // Initialize cors to allow all origins
        public CorsConfig() {
            cors.addAllowedOrigin("*"); // Allow any origin
            cors.addAllowedMethod("*"); // Allow any HTTP methods
            cors.addAllowedHeader("*"); // Allow any headers
        }

    }
