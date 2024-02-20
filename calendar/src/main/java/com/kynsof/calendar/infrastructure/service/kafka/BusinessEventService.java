package com.kynsof.calendar.infrastructure.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsof.share.core.domain.kafka.entity.BusinessKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BusinessEventService {
    private final KafkaTemplate<String, String> producer;

    public BusinessEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void create(BusinessDto entity) {

        try {
            BusinessKafka event = new BusinessKafka(entity.getId(), entity.getName(), entity.getDescription(), entity.getRuc(), entity.getStatus().name());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));

            this.producer.send("business", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BusinessEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}