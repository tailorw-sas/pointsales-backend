package com.kynsof.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CalendarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalendarApplication.class, args);
    }


}
