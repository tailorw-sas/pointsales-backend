package com.kynsoft.gateway.application.service.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.event.EventType;
import com.kynsoft.gateway.application.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventsService {


    private final KafkaTemplate<String, String> producer;

    @Value("${topic.customer.name:customers}")
    private String topicCustomer;

    public CustomerEventsService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }


    public void publish(RegisterDTO customer) {


        CustomerCreatedEvent created = new CustomerCreatedEvent();
        created.setData(customer); // Aquí asumo que setData acepta una String
        created.setType(EventType.CREATED);
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
