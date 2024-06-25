package com.kynsof.calendar.infrastructure.service.kafka.consumer.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.enumType.PatientStatus;
import com.kynsof.calendar.domain.service.IPatientsService;
import com.kynsof.share.core.domain.kafka.entity.CustomerKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientEventService {

    @Autowired
    private IPatientsService service;

    @KafkaListener(topics = "medinec-create-patient", groupId = "calendar-patient")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            CustomerKafka eventRead = objectMapper.treeToValue(rootNode,
                    CustomerKafka.class);
            this.service.create(new PatientDto(
                    UUID.fromString(eventRead.getId()), "",
                    eventRead.getIdentificationNumber(),
                    eventRead.getFirstName(),
                    eventRead.getLastName(),
                    PatientStatus.ACTIVE,
                    eventRead.getImage() != null ? UUID.fromString(eventRead.getImage()) : null
            ));

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerPatientEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
