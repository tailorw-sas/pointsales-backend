package com.kynsof.scheduled.infrastructure.service.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.scheduled.domain.event.BusinessKafka;
import com.kynsof.scheduled.infrastructure.config.kafka.EventType;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerBusinessEventService {

    @KafkaListener(topics = "business")
    public void consumer(String event) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            BusinessKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), BusinessKafka.class);
            EventType eventType = objectMapper.treeToValue(rootNode.get("type"), EventType.class);

            if (eventType.equals(EventType.CREATED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN CREATED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

                System.err.println("BusinessKafka: " + eventRead.toString());
                System.err.println("Type: " + eventType.name());

            }
            if (eventType.equals(EventType.DELETED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN DELETED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

            }
            if (eventType.equals(EventType.UPDATED)) {
                //Definir accion
                System.err.println("#######################################################");
                System.err.println("#######################################################");
                System.err.println("SE EJECUTA UN DELETED");
                System.err.println("#######################################################");
                System.err.println("#######################################################");

            }
        } catch (Exception ex) {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("SE LANZA ERROR");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            Logger.getLogger(ConsumerBusinessEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
