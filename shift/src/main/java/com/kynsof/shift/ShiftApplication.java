package com.kynsof.shift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ShiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiftApplication.class, args);
    }
}
