package com.kynsof.identity.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.identity.domain.dto.UserPermissionBusinessDto;
import com.kynsof.share.core.domain.kafka.entity.UserBusinessKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerCreateUserBusinessEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerCreateUserBusinessEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void create(UserPermissionBusinessDto entity) {

        try {
            UserBusinessKafka event = new UserBusinessKafka(
                    entity.getUser().getId(), 
                    entity.getBusiness().getId()
            );

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));
            this.producer.send("user-busines", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerCreateUserBusinessEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}