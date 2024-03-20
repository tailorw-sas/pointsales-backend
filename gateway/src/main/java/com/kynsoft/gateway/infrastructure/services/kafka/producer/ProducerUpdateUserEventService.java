package com.kynsoft.gateway.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsoft.gateway.application.dto.UserRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerUpdateUserEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerUpdateUserEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    @Async
    public void update(UserRequest entity, String clientId) {

        try {
            UserKafka event = new UserKafka(clientId, entity.getUsername(), entity.getEmail(), entity.getFirstname(), entity.getLastname(), "", "", "", "");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.UPDATED));

            this.producer.send("user", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerUpdateUserEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}