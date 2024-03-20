package com.kynsoft.gateway.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.RoleKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerRegisterRoleEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerRegisterRoleEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    @Async
    public void create(String Id, String name, String description) {

        try {
            RoleKafka event = new RoleKafka(UUID.fromString(Id), name, description);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));

            this.producer.send("role", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerRegisterRoleEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}