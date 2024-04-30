package com.kynsof.treatments.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.UserType;
import com.kynsof.share.core.domain.kafka.entity.UserSystemKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsof.treatments.domain.dto.DoctorDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerUserSystemEventService {
    @Autowired
    private IDoctorService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user-system", groupId = "user-system-treatments")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserSystemKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserSystemKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED) && eventRead != null && eventRead.getUserType().equals(UserType.DOCTORS)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN CREATED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                this.service.create(new DoctorDto(eventRead.getId(), "", eventRead.getName(), eventRead.getLastName(), eventRead.getIdImage().toString(), Status.ACTIVE));
            }
            if (eventType.equals(EventType.DELETED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN DELETED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

            }
            if (eventType.equals(EventType.UPDATED) && eventRead != null && eventRead.getUserType().equals(UserType.DOCTORS)) {
                //Definir accion
                //Definir accion
                DoctorDto doctorDto = new DoctorDto(eventRead.getId(), "", eventRead.getName(), eventRead.getLastName(), eventRead.getIdImage().toString(), Status.ACTIVE);
                this.service.create(doctorDto);
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerUserSystemEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
