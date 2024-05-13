package com.kynsof.calendar.infrastructure.service.kafka.consumer.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.enumType.PatientStatus;
import com.kynsof.calendar.domain.service.IPatientsService;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerDependentPatientsEventService {
    @Autowired
    private IPatientsService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user-dependent", groupId = "user-dependent-calendar")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED)) {
                //Definir accion
                this.service.create(new PatientDto(UUID.fromString(eventRead.getId()),
                        eventRead.getIdentification(), eventRead.getFirstname(), eventRead.getLastname(), "",
                        PatientStatus.ACTIVE, null));
            }
            if (eventType.equals(EventType.DELETED)) {
                //Definir accion
            }
            if (eventType.equals(EventType.UPDATED)) {
                //Definir accion
                this.service.update(new PatientDto(
                        UUID.fromString(eventRead.getId()), 
                        eventRead.getIdentification(), 
                        eventRead.getFirstname(),
                        eventRead.getLastname(), 
                        eventRead.getGender(), 
                        PatientStatus.ACTIVE, 
                        eventRead.getPhone() != null ? UUID.fromString(eventRead.getPhone()) : null
                ));
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerDependentPatientsEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}