package com.kynsof.identity.infrastructure.services.kafka.consumer.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.identity.application.command.user.create.CreateUserSystemCommand;
import com.kynsof.identity.infrastructure.PasswordGenerator;
import com.kynsof.share.core.domain.EUserType;
import com.kynsof.share.core.domain.kafka.entity.CustomerKafka;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientEventService {

    private final IMediator mediator;
    private final ObjectMapper objectMapper;

    public ConsumerPatientEventService(IMediator mediator, ObjectMapper objectMapper) {
        this.mediator = mediator;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "medinec-create-patient", groupId = "identity-patient")
    public void listen(String event) {
        try {

            JsonNode rootNode = objectMapper.readTree(event);
            CustomerKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), CustomerKafka.class);
            CreateUserSystemCommand command = new CreateUserSystemCommand(
                   UUID.fromString(eventRead.getId()),
                    eventRead.getEmail(),
                    eventRead.getEmail(),
                    eventRead.getFirstName(),
                    eventRead.getLastName(),
                    PasswordGenerator.generatePassword(),
                    EUserType.PATIENTS,
                    eventRead.getImage()
            );
            mediator.send(command);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerPatientEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
