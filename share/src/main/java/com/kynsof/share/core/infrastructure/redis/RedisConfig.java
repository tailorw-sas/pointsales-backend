package com.kynsof.share.core.infrastructure.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${REDIS_ADDRESS:localhost}")
    private String redisAddress;

    @Value("${REDIS_PORT:6379}")
    private Integer redisPort;

    @Value("${REDIS_USERNAME:}") // Username opcional
    private String redisUsername;

    @Value("${REDIS_PASSWORD:}") // Password opcional
    private String redisPassword;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisAddress, redisPort);

        if (!redisUsername.isEmpty()) {
            redisConfig.setUsername(redisUsername);
        }
        if (!redisPassword.isEmpty()) {
            redisConfig.setPassword(redisPassword);
        }

        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisConfig);

        // Agregar log de verificación
        factory.afterPropertiesSet(); // Para asegurar que la configuración esté completa
        logger.info("Redis connection established with address: {}:{} and username: {}", redisAddress, redisPort, redisUsername.isEmpty() ? "none" : redisUsername);

        return factory;
    }
}