package com.kynsof.patients.services.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateUserEventService {


    // Ejemplo de un método listener
    @KafkaListener(topics = "customers", groupId = "grupo1")
    public void listen(String event)  {
        // Lógica para manejar el evento
//        try {
//            JsonProcessor processor = new JsonProcessor();
//            processor.processJson(event);
           // CustomerCreatedEvent customer = objectMapper.readValue(event, CustomerCreatedEvent.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("Received event: " + event);
    }

}

