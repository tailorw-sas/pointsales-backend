package com.kynsof.treatments.infrastructure.service.kafka.consumer.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.CustomerKafka;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IPatientsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientEventService {

    private final IPatientsService service;

    public ConsumerPatientEventService(IPatientsService service) {
        this.service = service;
    }

    @KafkaListener(topics = "medinec-create-patient", groupId = "calendar-treatments")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            CustomerKafka eventRead = objectMapper.treeToValue(rootNode.get("data"),
                    CustomerKafka.class);

            this.service.create(new PatientDto(
                    UUID.fromString(eventRead.getId()),
                    eventRead.getEmail(),
                    eventRead.getIdentificationNumber(),
                    eventRead.getFirstName(),
                    eventRead.getLastName(),
                    Status.ACTIVE,
                    eventRead.getBirthDate()
            ));

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerPatientEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
