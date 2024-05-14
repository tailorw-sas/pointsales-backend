package com.kynsof.calendar.infrastructure.service.kafka.consumer.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.enumType.PatientStatus;
import com.kynsof.calendar.domain.service.IPatientsService;
import com.kynsof.share.core.domain.kafka.entity.PatientKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientUpdateEventService {

    @Autowired
    private IPatientsService service;

    @KafkaListener(topics = "patient-update", groupId = "calendar-patient")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            PatientKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), PatientKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

                this.service.update(new PatientDto(
                        UUID.fromString(eventRead.getId()),
                        eventRead.getIdentification(), "",
                        eventRead.getName(),
                        eventRead.getLastName(),
                        eventRead.getGender(),
                        PatientStatus.ACTIVE,
                        eventRead.getLogo() != null ? UUID.fromString(eventRead.getLogo()) : null
                ));

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerPatientUpdateEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
