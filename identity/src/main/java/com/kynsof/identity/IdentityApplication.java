package com.kynsof.identity;

import com.kynsof.identity.infrastructure.services.ModuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class IdentityApplication implements CommandLineRunner {

    @Autowired
    private ModuleServiceImpl moduleServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.moduleServiceImpl.updateDelete();
    }

}
