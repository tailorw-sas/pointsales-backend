package com.kynsof.identity.infrastructure.services.kafka.consumer.users.assistant;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.identity.application.command.user.create.CreateUserSystemCommand;
import com.kynsof.identity.infrastructure.PasswordGenerator;
import com.kynsof.share.core.domain.EUserType;
import com.kynsof.share.core.domain.kafka.entity.DoctorKafka;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerCreateAssistantEventService {
    private final IMediator mediator;

    public ConsumerCreateAssistantEventService(IMediator mediator) {

        this.mediator = mediator;
    }

    @KafkaListener(topics = "medinec-replicate-assistant", groupId = "identity-doctor")
    public void listen(String event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);
            DoctorKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), DoctorKafka.class);

            CreateUserSystemCommand command = new CreateUserSystemCommand(
                    eventRead.getId(),
                    eventRead.getEmail(),
                    eventRead.getEmail(),
                    eventRead.getName(),
                    eventRead.getLastName(),
                    PasswordGenerator.generatePassword(),
                    EUserType.ASSISTANTS,
                    eventRead.getImage()
            );
            mediator.send(command);

        } catch (Exception ex) {
            Logger.getLogger(ConsumerCreateAssistantEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
