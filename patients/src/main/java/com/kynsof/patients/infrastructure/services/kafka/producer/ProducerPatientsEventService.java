package com.kynsof.patients.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.share.core.domain.kafka.entity.PatientKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerPatientsEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerPatientsEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void create(PatientDto entity) {

        try {

            PatientKafka event = new PatientKafka(entity.getId(), entity.getIdentification(), entity.getName(), entity.getLastName(), entity.getGender(), entity.getStatus().name());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));

            this.producer.send("patient", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerPatientsEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}