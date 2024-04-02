package com.kynsof.calendar.infrastructure.service.kafka.consumer;

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

    @KafkaListener(topics = "payment", groupId = "payment-calendar")
    public void consumer(PaymentStatus event) {

        try {
            String status = event.getStatus();
        } catch (Exception ex) {
            Logger.getLogger(ConsumerPaymentEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
