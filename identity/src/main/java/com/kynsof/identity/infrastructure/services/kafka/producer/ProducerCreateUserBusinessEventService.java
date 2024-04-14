package com.kynsof.identity.infrastructure.services.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.identity.domain.dto.UserPermissionBusinessDto;
import com.kynsof.identity.domain.interfaces.service.IUserPermissionBusinessService;
import com.kynsof.share.core.domain.kafka.entity.UserBusinessKafka;
import com.kynsof.share.core.domain.kafka.event.CreateEvent;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProducerCreateUserBusinessEventService {
    private final KafkaTemplate<String, String> producer;

    private final IUserPermissionBusinessService userPermissionBusinessService;

    public ProducerCreateUserBusinessEventService(KafkaTemplate<String, String> producer,
                                                  IUserPermissionBusinessService userPermissionBusinessService) {
        this.producer = producer;
        this.userPermissionBusinessService = userPermissionBusinessService;
    }

    public void create(UserPermissionBusinessDto entity) {

        try {
            UserBusinessKafka event = new UserBusinessKafka(
                    entity.getUser().getId(), 
                    entity.getBusiness().getId()
            );
            Long cant = userPermissionBusinessService.countByUserAndBusiness(event.getIdUser(), event.getIdBusiness());
            if (cant == 1) {
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN CREATED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(new CreateEvent<>(event, EventType.CREATED));
                this.producer.send("user-business", json);
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducerCreateUserBusinessEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}