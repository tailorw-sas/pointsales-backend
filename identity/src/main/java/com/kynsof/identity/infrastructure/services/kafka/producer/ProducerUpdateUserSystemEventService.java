package com.kynsof.identity.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.share.core.domain.kafka.entity.UserSystemKakfa;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerUpdateUserSystemEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerUpdateUserSystemEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    @Async
    public void update(UserSystemDto entity) {
        try {
            UserSystemKakfa event = new UserSystemKakfa(
                    entity.getId(), 
                    entity.getUserName(), 
                    entity.getEmail(), 
                    entity.getName(), 
                    entity.getLastName(),
                    null,
                    entity.getIdImage()
            );

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.UPDATED));

            this.producer.send("user-system", json);
            System.err.println("##############################################");
            System.err.println("##############################################");
            System.err.println("SE LANZO EL EVENTO DEL USER SYSTEM");
            System.err.println("##############################################");
            System.err.println("##############################################");
            System.err.println("##############################################");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerUpdateUserSystemEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}