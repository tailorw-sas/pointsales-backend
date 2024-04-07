package com.kynsof.treatments.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.PatientKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IPatientsService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientEventService {

    @Autowired
    private IPatientsService service;

    @KafkaListener(topics = "patient", groupId = "treatments-patient")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            PatientKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), PatientKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED)) {
                this.service.create(new PatientDto(
                        UUID.fromString(eventRead.getId()),
                        eventRead.getIdentification(),
                        eventRead.getName(),
                        eventRead.getLastName(),
                        eventRead.getGender(),
                        Status.ACTIVE,
                        null
                ));
            }
            if (eventType.equals(EventType.UPDATED)) {
                this.service.update(new PatientDto(
                        UUID.fromString(eventRead.getId()),
                        eventRead.getIdentification(),
                        eventRead.getName(),
                        eventRead.getLastName(),
                        eventRead.getGender(),
                        Status.ACTIVE,
                        null
                ));
            }

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerPatientEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}