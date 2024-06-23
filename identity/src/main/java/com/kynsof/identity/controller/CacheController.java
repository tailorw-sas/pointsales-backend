package com.kynsof.identity.controller;

import com.kynsof.identity.infrastructure.services.kafka.CacheService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @DeleteMapping("/kafka")
    public void clearKafkaCache() {
        cacheService.clearKafkaCache();
    }
}
