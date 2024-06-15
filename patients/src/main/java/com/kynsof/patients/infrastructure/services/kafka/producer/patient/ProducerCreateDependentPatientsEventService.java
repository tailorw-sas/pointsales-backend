package com.kynsof.patients.infrastructure.services.kafka.producer.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerCreateDependentPatientsEventService {
    private final KafkaTemplate<String, String> producer;

    public ProducerCreateDependentPatientsEventService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void create(PatientDto entity, LocalDate birthdayDate) {

        try {

            UserKafka event = new UserKafka(
                    entity.getId().toString(), 
                    entity.getIdentification(), 
                    "", 
                    entity.getName(), 
                    entity.getLastName(), 
                    entity.getIdentification(), 
                    entity.getPhoto(), 
                    entity.getGender().toString(), 
                    entity.getStatus().toString()
            );

            event.setBirthdayDate(birthdayDate.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));

            this.producer.send("user-dependent", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerCreateDependentPatientsEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}