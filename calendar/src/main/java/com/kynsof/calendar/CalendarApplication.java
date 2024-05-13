package com.kynsof.calendar;

import com.kynsof.calendar.infrastructure.service.ScheduleServiceImpl;
import com.kynsof.calendar.infrastructure.service.ServiceServiceImpl;
import com.kynsof.calendar.infrastructure.service.ServiceTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CalendarApplication  implements CommandLineRunner {

    @Autowired
    private ServiceTypeServiceImpl serviceImpl;

    @Autowired
    private ServiceServiceImpl serviceServiceImpl;

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(CalendarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        serviceImpl.updateDelete();
        serviceServiceImpl.updateDelete();
        scheduleServiceImpl.updateDelete();
    }

}
