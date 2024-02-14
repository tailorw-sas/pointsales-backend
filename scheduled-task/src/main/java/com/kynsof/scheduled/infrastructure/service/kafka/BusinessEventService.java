package com.kynsof.scheduled.infrastructure.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.event.BusinessKafka;
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
            BusinessKafka event = new BusinessKafka(entity);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(event);

            this.producer.send("business", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BusinessEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}