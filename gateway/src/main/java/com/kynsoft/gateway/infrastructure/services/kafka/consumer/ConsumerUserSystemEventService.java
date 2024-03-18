package com.kynsoft.gateway.infrastructure.services.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.UserKafka;
import com.kynsof.share.core.domain.kafka.entity.UserSystemKakfa;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import java.util.Set;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ConsumerUserSystemEventService {
    @Autowired
    private IUserService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user-system", groupId = "user-system-gateway")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserSystemKakfa eventRead = objectMapper.treeToValue(rootNode.get("data"), UserSystemKakfa.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN CREATED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                this.service.registerUser(new RegisterDTO(eventRead.getUserName(), eventRead.getEmail(), eventRead.getName(), eventRead.getLastName(), "", Set.of()));
            }
            if (eventType.equals(EventType.DELETED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN DELETED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

            }
            if (eventType.equals(EventType.UPDATED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN EVENTO DE ACTUALIZACION");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

                //this.service.update(new PatientDto(UUID.fromString(eventRead.getId()), "", eventRead.getFirstname(), eventRead.getLastname(), "", PatientStatus.ACTIVE));
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerUserSystemEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
