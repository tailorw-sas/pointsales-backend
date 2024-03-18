package com.kynsoft.gateway.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Configura los orígenes permitidos
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("https://example.com"); // Añade más orígenes según sea necesario

        // Configura los métodos HTTP permitidos
        config.addAllowedMethod("*");

        // Configura las cabeceras permitidas
        config.addAllowedHeader("*");

        // Configura el soporte para credenciales
        config.setAllowCredentials(true);

        // Aplica la configuración de CORS a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
