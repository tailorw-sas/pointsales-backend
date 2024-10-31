package com.kynsof.share.core.infrastructure.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

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

        return new LettuceConnectionFactory(redisConfig);
    }
}