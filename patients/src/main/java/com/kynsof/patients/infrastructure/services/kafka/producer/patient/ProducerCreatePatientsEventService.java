package com.kynsof.patients.infrastructure.services.kafka.producer.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.share.core.domain.kafka.entity.PatientKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import java.time.LocalDate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerCreatePatientsEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerCreatePatientsEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void create(PatientDto entity, LocalDate birthdayDate) {

        try {

            PatientKafka event = new PatientKafka(
                    entity.getId().toString(), 
                    entity.getIdentification(), 
                    entity.getName(), 
                    entity.getLastName(),
                    entity.getGender().toString(), 
                    entity.getStatus().name(), 
                    entity.getPhoto() != null ? entity.getPhoto() : null
            );

            if (birthdayDate != null) {
                event.setBirthdayDate(birthdayDate.toString());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));

            this.producer.send("patient", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerCreatePatientsEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}