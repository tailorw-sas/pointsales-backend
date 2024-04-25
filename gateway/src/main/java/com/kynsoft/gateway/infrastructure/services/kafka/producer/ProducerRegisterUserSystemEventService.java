package com.kynsoft.gateway.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.UserSystemKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsoft.gateway.domain.dto.user.UserSystemRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerRegisterUserSystemEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerRegisterUserSystemEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    @Async
    public void create(UserSystemRequest entity, String clientId) {

        try {
            UserSystemKafka event = new UserSystemKafka(
                    UUID.fromString(clientId), 
                    entity.getUserName(),
                    entity.getEmail(), 
                    entity.getName(),
                    entity.getLastName(),
                   null,
                    null,
                    entity.getUserType()

            );

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));

            this.producer.send("user-system", json);
            System.err.println("##############################################");
            System.err.println("##############################################");
            System.err.println("SE LANZO EL EVENTO DEL USER SYSTEM");
            System.err.println("##############################################");
            System.err.println("##############################################");
            System.err.println("##############################################");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerRegisterUserSystemEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}