package com.kynsof.patients.infrastructure.services.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreateUserEventService {

    // Ejemplo de un método listener
    @KafkaListener(topics = "patient", groupId = "patient-patient")
    public void listen(String event) {
        System.err.println("#######################################################");
        System.err.println("#######################################################");
        System.err.println("SE EJECUTA UN CREATED DESDE PATIENT");
        System.err.println("#######################################################");
        System.err.println("#######################################################");

        System.out.println("Received event: " + event);
    }

}
