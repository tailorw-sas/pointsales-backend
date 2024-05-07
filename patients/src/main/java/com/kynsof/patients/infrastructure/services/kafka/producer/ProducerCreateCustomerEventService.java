package com.kynsof.patients.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.CustomerKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerCreateCustomerEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerCreateCustomerEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void create(CustomerKafka entity) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(entity, EventType.CREATED));

            this.producer.send("create-custumer", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerCreateCustomerEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}