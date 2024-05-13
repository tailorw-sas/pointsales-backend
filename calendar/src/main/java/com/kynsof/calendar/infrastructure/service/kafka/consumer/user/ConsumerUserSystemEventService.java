package com.kynsof.calendar.infrastructure.service.kafka.consumer.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.EUserType;
import com.kynsof.share.core.domain.kafka.entity.UserSystemKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerUserSystemEventService {
    @Autowired
    private IResourceService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user-system", groupId = "user-system-calendar")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserSystemKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserSystemKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED) && eventRead != null && eventRead.getUserType().equals(EUserType.DOCTORS)) {
                //Definir accion
                ResourceDto resourceDto = new ResourceDto(eventRead.getId(), eventRead.getName()+" "+ eventRead.getLastName(),
                        "", "", EResourceStatus.ACTIVE, false, eventRead.getIdImage());
                this.service.create(resourceDto);
            }
            if (eventType.equals(EventType.DELETED)) {
                //Definir accion
            }
            if (eventType.equals(EventType.UPDATED)&& eventRead != null && eventRead.getUserType().equals(EUserType.DOCTORS)) {
                //Definir accion
                ResourceDto resourceDto = new ResourceDto(eventRead.getId(), eventRead.getName(), "", "", EResourceStatus.ACTIVE, false, eventRead.getIdImage());
                resourceDto.setImage(eventRead.getIdImage());
                this.service.update(resourceDto);
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerUserSystemEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}