package com.kynsof.calendar.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.kafka.entity.UserSystemKakfa;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerResourceEventService {
    @Autowired
    private IResourceService service;

    // Ejemplo de un método listener
//    @KafkaListener(topics = "resource", groupId = "resource-calendar")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserSystemKakfa eventRead = objectMapper.treeToValue(rootNode.get("data"), UserSystemKakfa.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED)) {
                //Definir accion
                ResourceDto resourceDto = new ResourceDto(eventRead.getId(), eventRead.getName() + " " + eventRead.getLastName(), "", "", EResourceStatus.ACTIVE, true, eventRead.getIdImage());
                resourceDto.setImage(eventRead.getIdImage());
                this.service.create(resourceDto);
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerResourceEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
