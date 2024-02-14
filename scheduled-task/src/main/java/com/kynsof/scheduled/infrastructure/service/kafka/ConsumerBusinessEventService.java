package com.kynsof.scheduled.infrastructure.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.scheduled.domain.event.BusinessKafka;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerBusinessEventService {

    @KafkaListener(topics = "business")
    public void create(String event)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            BusinessKafka eventRead = objectMapper.readValue(event, BusinessKafka.class);
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("DATOS RECIBIDOS DEL EVENTO");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            System.err.println("ID: " + eventRead.getId());
            System.err.println("Nombre: " + eventRead.getName());
            System.err.println("Descripcion: " + eventRead.getDescription());

            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("DATOS RECIBIDOS DEL EVENTO");
            System.err.println("#######################################################");
            System.err.println("#######################################################");
        } catch (JsonProcessingException ex) {
            System.err.println("#######################################################");
            System.err.println("#######################################################");
            System.err.println("SE LANZA ERROR");
            System.err.println("#######################################################");
            System.err.println("#######################################################");

            Logger.getLogger(ConsumerBusinessEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

