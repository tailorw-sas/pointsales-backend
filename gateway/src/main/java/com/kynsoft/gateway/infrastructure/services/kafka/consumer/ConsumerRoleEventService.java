package com.kynsoft.gateway.infrastructure.services.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.RoleKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsoft.gateway.domain.dto.role.RoleRequest;
import com.kynsoft.gateway.domain.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerRoleEventService {
    @Autowired
    private IRoleService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "role", groupId = "role-identity")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            RoleKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), RoleKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);
            
            /**
             * Solo acciona si es un evento de role para UPDATE
             */
            if (eventType.equals(EventType.UPDATED)) {
                this.service.updateRole(new RoleRequest(eventRead.getName(), eventRead.getDescription()), eventRead.getId());
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerRoleEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
