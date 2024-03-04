package com.kynsoft.notification.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsoft.notification.application.SendEmailRequest;
import com.kynsoft.notification.infrastructure.service.EmailService;
import com.kynsoft.notification.shared.domain.event.EventType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ConsumerEmailEventService {
    @Autowired
    private EmailService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "email", groupId = "email")
    public void listen(String event) {
        try {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("SE EJECUTA UN ENVIO DE CORREO");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            SendEmailRequest eventRead = objectMapper.treeToValue(rootNode.get("data"), SendEmailRequest.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            System.out.println("Received event: " + event);
            this.service.sendMail(eventRead.getToEmail(), eventRead.getSubject(), eventRead.getMessage());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerEmailEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
