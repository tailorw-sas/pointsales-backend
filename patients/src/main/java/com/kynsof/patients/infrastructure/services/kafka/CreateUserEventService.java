package com.kynsof.patients.infrastructure.services.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateUserEventService {

    // Ejemplo de un método listener
    @KafkaListener(topics = "patient")
    public void listen(String event) {
        System.err.println("#######################################################");
        System.err.println("#######################################################");
        System.err.println("SE EJECUTA UN CREATED DESDE PATIENT");
        System.err.println("#######################################################");
        System.err.println("#######################################################");

        System.out.println("Received event: " + event);
    }

}
