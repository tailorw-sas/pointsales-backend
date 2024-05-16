package com.kynsof.calendar.infrastructure.service.kafka.consumer.userbusiness;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.share.core.domain.kafka.entity.DeleteUserBusinessKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerDeleteUserBusinessRelationEventService {

    @Autowired
    private IBusinessResourceService businessResourceService;

    // Ejemplo de un método listener
    @KafkaListener(topics = "user-business-delete", groupId = "calendar-user-business-delete")
    public void listen(String event) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            DeleteUserBusinessKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), DeleteUserBusinessKafka.class);
            BusinessResourceDto delete = this.businessResourceService.findBusinessResourceByBusinessIdAndResourceId(eventRead.getBusinessId(), eventRead.getUserId());
            this.businessResourceService.delete(delete);
            System.err.println("##############################################");
            System.err.println("##############################################");
            System.err.println("SE ELIMINO");
            System.err.println("##############################################");
            System.err.println("##############################################");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerDeleteUserBusinessRelationEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
