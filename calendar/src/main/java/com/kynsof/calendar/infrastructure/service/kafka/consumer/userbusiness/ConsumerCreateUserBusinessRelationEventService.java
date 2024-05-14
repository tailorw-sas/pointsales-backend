package com.kynsof.calendar.infrastructure.service.kafka.consumer.userbusiness;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.kafka.entity.UserBusinessKafka;
import com.kynsof.share.core.domain.kafka.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerCreateUserBusinessRelationEventService {
    @Autowired
    private IResourceService resourceService;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user-business", groupId = "calendar-user-business")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserBusinessKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), UserBusinessKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN CREATED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                resourceService.addBusiness(eventRead.getIdBusiness(), eventRead.getIdUser(), LocalDate.now());
//                BusinessDto businessDto = this.businessService.findById(eventRead.getIdBusiness());
//                UserSystemDto userSystemDto = this.userSystemService.findById(eventRead.getIdUser());
//
//                this.service.create(new UserBusinessRelationDto(UUID.randomUUID(), userSystemDto, businessDto, "ACTIVE", ConfigureTimeZone.getTimeZone()));
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerCreateUserBusinessRelationEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
