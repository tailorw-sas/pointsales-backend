package com.kynsof.identity.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.share.core.domain.kafka.entity.BusinessKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerUpdateBusinessEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerUpdateBusinessEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void update(BusinessDto entity) {

        try {
            BusinessKafka event = new BusinessKafka(entity.getId(), entity.getName(), entity.getLatitude(), entity.getLongitude(),
                    UUID.fromString(entity.getLogo()));

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.UPDATED));
            this.producer.send("busines", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerUpdateBusinessEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}