package com.kynsof.patients.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerDependentPatientsEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerDependentPatientsEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void create(DependentPatientDto entity) {

        try {

            UserKafka event = new UserKafka(
                    entity.getId().toString(), 
                    entity.getIdentification(), 
                    "", 
                    entity.getName(), 
                    entity.getLastName(), 
                    entity.getIdentification(), 
                    null, 
                    entity.getGender().toString(), 
                    entity.getStatus().toString()
            );

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));

            this.producer.send("user-dependent", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerDependentPatientsEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}