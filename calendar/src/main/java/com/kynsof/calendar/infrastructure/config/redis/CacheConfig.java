package com.kynsof.calendar.infrastructure.config.redis;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.annotation.EnableCaching;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String USER_CACHE = "user-cache";
    public static final String QUALIFICATION_CACHE = "qualification-cache";
    public static final String BUSINESS_CACHE = "business-cache";
    public static final String PATIENTS_CACHE = "patients-cache";
    public static final String RECEIPT_CACHE = "receipt-cache";
    public static final String RESOURCE_CACHE = "resource-cache";
    public static final String SCHEDULE_CACHE = "schedule-cache";
    public static final String SERVICE_CACHE = "service-cache";

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put(USER_CACHE, createConfig(1, ChronoUnit.MINUTES));
        redisCacheConfigurationMap.put(QUALIFICATION_CACHE, createConfig(1, ChronoUnit.MINUTES));
        redisCacheConfigurationMap.put(BUSINESS_CACHE, createConfig(1, ChronoUnit.MINUTES));
        redisCacheConfigurationMap.put(PATIENTS_CACHE, createConfig(1, ChronoUnit.MINUTES));
        redisCacheConfigurationMap.put(RECEIPT_CACHE, createConfig(1, ChronoUnit.MINUTES));
        redisCacheConfigurationMap.put(RESOURCE_CACHE, createConfig(1, ChronoUnit.MINUTES));
        redisCacheConfigurationMap.put(SCHEDULE_CACHE, createConfig(1, ChronoUnit.MINUTES));
        redisCacheConfigurationMap.put(SERVICE_CACHE, createConfig(1, ChronoUnit.MINUTES));

        return RedisCacheManager
            .builder(redisConnectionFactory)
            .withInitialCacheConfigurations(redisCacheConfigurationMap)
            .build();
    }

    private static RedisCacheConfiguration createConfig(long time, ChronoUnit temporalUnit) {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.of(time, temporalUnit));
    }
}