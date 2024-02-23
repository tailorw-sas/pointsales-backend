package com.kynsof.calendar.infrastructure.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.PatientKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientEventService {

    // Ejemplo de un método listener
    @KafkaListener(topics = "patient", groupId = "calendar-patient")
    public void listen(String event) {
        try {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("SE EJECUTA UN CREATED DESDE PATIENT");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            PatientKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), PatientKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            System.out.println("Received event: " + event);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerPatientEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
