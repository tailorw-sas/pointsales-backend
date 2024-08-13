package com.kynsof.calendar.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.calendar.application.command.receipt.update.UpdateReceiptCommand;
import com.kynsof.calendar.domain.dto.PaymentStatus;
import com.kynsof.share.core.infrastructure.bus.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerPaymentEventService {

    @Autowired
    private IMediator mediator;

    @KafkaListener(topics = "payment", groupId = "payment-calendar", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(String event) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PaymentStatus paymentStatus = objectMapper.readValue(event, PaymentStatus.class);

            UpdateReceiptCommand command = new UpdateReceiptCommand(paymentStatus.getRequestId(), paymentStatus.getStatus());
            mediator.send(command);

        } catch (Exception ex) {
            Logger.getLogger(ConsumerPaymentEventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
