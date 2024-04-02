package com.kynsof.calendar.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.domain.dto.PaymentStatus;
import com.kynsof.calendar.domain.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPaymentEventService {

    @Autowired
    private IBusinessService service;

    @KafkaListener(topics = "payment", groupId = "payment-calendar", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(String event) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String status = event;
            PaymentStatus paymentStatus = objectMapper.readValue(event, PaymentStatus.class);
            String a = paymentStatus.getStatus();
        } catch (Exception ex) {
            Logger.getLogger(ConsumerPaymentEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
