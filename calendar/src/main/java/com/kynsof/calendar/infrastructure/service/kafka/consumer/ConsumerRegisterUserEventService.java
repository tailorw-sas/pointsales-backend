package com.kynsof.calendar.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerRegisterUserEventService {

    @KafkaListener(topics = "user", groupId = "calendar-register-user")
    public void consumer(String event) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN CREATED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

                System.err.println("UserKafka: " + eventRead.toString());
                System.err.println("Type: " + eventType.name());

            }
            if (eventType.equals(EventType.DELETED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN DELETED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

            }
            if (eventType.equals(EventType.UPDATED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN DELETED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

            }
        } catch (Exception ex) {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("SE LANZA ERROR");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            Logger.getLogger(ConsumerRegisterUserEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
