package com.kynsoft.notification.infrastructure.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsof.share.core.domain.kafka.entity.UserOtpKafka;
import com.kynsoft.notification.application.dto.EmailRequest;
import com.kynsoft.notification.application.dto.MailJetRecipient;
import com.kynsoft.notification.application.dto.MailJetVar;
import com.kynsoft.notification.domain.service.IEmailService;
import com.mailjet.client.errors.MailjetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsumerTriggerPasswordResetEventService {
    @Autowired
    private IEmailService service;

    // Ejemplo de un método listener
    @KafkaListener(topics = "otp", groupId = "otp")
    public void listen(String event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(event);

            UserOtpKafka otpKafka = objectMapper.treeToValue(rootNode.get("data"), UserOtpKafka.class);
            List<MailJetRecipient> mailJetRecipients = new ArrayList<>();
            mailJetRecipients.add(new MailJetRecipient(otpKafka.getEmail(),otpKafka.getName()));

            List<MailJetVar> vars = Arrays.asList(
                    new MailJetVar("username", otpKafka.getName()),
                    new MailJetVar("otp_token", otpKafka.getOtpCode())
            );

            int  templateId =3931552;
            EmailRequest emailRequest = new EmailRequest(mailJetRecipients, vars, new ArrayList<>(),"Código Otp", templateId);
            this.service.sendEmailMailjet(emailRequest);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConsumerTriggerPasswordResetEventService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (  MailjetException e) {
            throw new RuntimeException(e);
        }
    }

}
