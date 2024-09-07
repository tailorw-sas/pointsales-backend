package com.kynsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CloudBridges {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "true");
        SpringApplication.run(CloudBridges.class, args);

    }
}
