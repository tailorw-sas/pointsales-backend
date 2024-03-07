package com.kynsof.store.infrastructure.services.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsof.store.domain.dto.CustomerDto;
import com.kynsof.store.domain.services.ICustomerService;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ConsumerUserEventService {
    @Autowired
    private ICustomerService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user", groupId = "customer")
    public void listen(String event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);
            
            if (eventType.equals(EventType.CREATED)) {
                //Definir accion
                this.service.create(new CustomerDto(UUID.fromString(eventRead.getId()), eventRead.getFirstname(), eventRead.getLastname(), eventRead.getEmail(), ""));

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
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerUserEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}