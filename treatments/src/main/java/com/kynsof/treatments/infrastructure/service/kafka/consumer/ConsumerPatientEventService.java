package com.kynsof.treatments.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.PatientKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientEventService {

    @Autowired
    private IPatientsService service;

    @KafkaListener(topics = "patient", groupId = "treatments-patient")
    public void listen(String event) {
        try {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("ENTRO");
            System.err.println("#######################################################");
            System.err.println("#######################################################");
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
                        LocalDate.parse(eventRead.getBirthdayDate())
                ));
            }
            if (eventType.equals(EventType.UPDATED)) {
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("EVENTO DE ACTUALIZA UN PACIENTE");
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                this.service.update(new PatientDto(
                        UUID.fromString(eventRead.getId()),
                        eventRead.getIdentification(),
                        eventRead.getName(),
                        eventRead.getLastName(),
                        eventRead.getGender(),
                        Status.ACTIVE,
                        LocalDate.parse(eventRead.getBirthdayDate())
                ));
            }

        } catch (JsonProcessingException ex) {
            System.err.println("########################");
            System.err.println("ERROR: " + ex.getMessage());
            Logger.getLogger(ConsumerPatientEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
