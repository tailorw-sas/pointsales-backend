package com.kynsoft.service.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsoft.config.kafka.EventType;
import com.kynsoft.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
public class CustomerEventsService {


    private KafkaTemplate<String, String> producer;

    @Value("${topic.customer.name:customers}")
    private String topicCustomer;

    public CustomerEventsService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }


    public void publish(RegisterDTO customer) {


        CustomerCreatedEvent created = new CustomerCreatedEvent();
        created.setData(customer); // Aquí asumo que setData acepta una String
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String customerAsJson = "";
        try {
            customerAsJson = objectMapper.writeValueAsString(created);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        }

        this.producer.send(topicCustomer, customerAsJson);
    }


}
