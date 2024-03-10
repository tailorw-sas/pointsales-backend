package com.kynsoft.notification.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.UserOtpKafka;
import com.kynsoft.notification.domain.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerTriggerPasswordResetEventService {
    @Autowired
    private IEmailService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "otp", groupId = "otp")
    public void listen(String event) {
        try {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("SE CONSUME UN ENVIO DE CORREO CON OTP");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserOtpKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserOtpKafka.class);
            this.service.sendMail(eventRead.getEmail(), "OTP code", eventRead.getOtpCode());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerTriggerPasswordResetEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
