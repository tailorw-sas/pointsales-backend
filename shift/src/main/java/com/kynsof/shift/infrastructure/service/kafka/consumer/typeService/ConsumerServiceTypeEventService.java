package com.kynsof.shift.infrastructure.service.kafka.consumer.typeService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.ServiceTypeKafka;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsof.shift.application.command.serviceType.replicate.create.CreateServiceTypeReplicateCommand;
import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerServiceTypeEventService {

    private final IMediator mediator;

    public ConsumerServiceTypeEventService(IMediator mediator) {
        this.mediator = mediator;
    }

    @KafkaListener(topics = "medic_service_type", groupId = "shift")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);
            ServiceTypeKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), ServiceTypeKafka.class);
            this.mediator.send(new CreateServiceTypeReplicateCommand(
                    eventRead.getId(), 
                    eventRead.getName(), 
                    eventRead.getPicture(), 
                    EServiceStatus.valueOf(eventRead.getStatus()), 
                    eventRead.getCode()
            ));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerServiceTypeEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
