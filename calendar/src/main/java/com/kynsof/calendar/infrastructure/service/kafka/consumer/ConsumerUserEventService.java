package com.kynsof.calendar.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.UserDto;
import com.kynsof.calendar.domain.service.IUserService;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ConsumerUserEventService {
    @Autowired
    private IUserService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user", groupId = "calendar-user")
    public void listen(String event) {
        try {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("SE EJECUTA UN CREATED DESDE PATIENT");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            System.out.println("Received event: " + event);
            this.service.create(new UserDto(UUID.fromString(eventRead.getId()), eventRead.getUsername(), eventRead.getEmail(), eventRead.getFirstname(), eventRead.getLastname()));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerUserEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
