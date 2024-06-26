package com.kynsof.identity.infrastructure.services.kafka.consumer.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.identity.domain.dto.CustomerDto;
import com.kynsof.identity.domain.interfaces.service.ICustomerService;
import com.kynsof.share.core.domain.kafka.entity.CustomerKafka;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPatientEventService {

    private final ICustomerService service;

    public ConsumerPatientEventService(ICustomerService service) {
        this.service = service;
    }

    @KafkaListener(topics = "medinec-create-patient", groupId = "identity-patient")
    public void listen(String event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            CustomerKafka eventRead = objectMapper.treeToValue(rootNode.get("data"), CustomerKafka.class);

            this.service.create(new CustomerDto(UUID.fromString(eventRead.getId()), eventRead.getFirstName(), eventRead.getLastName(), eventRead.getEmail(), ConfigureTimeZone.getTimeZone()));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerPatientEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
