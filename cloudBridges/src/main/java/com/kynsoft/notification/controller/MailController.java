package com.kynsoft.notification.controller;

import com.kynsof.share.core.infrastructure.bus.IMediator;
import com.kynsoft.notification.application.command.sendMailjetEmail.SendMailJetEMailCommand;
import com.kynsoft.notification.application.command.sendMailjetEmail.SendMailJetEMailRequest;
import com.kynsoft.notification.application.command.sendMailjetEmail.SendMailjetEmailMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {


    private final IMediator mediator;

    public MailController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/send/multi/keime")
    public ResponseEntity<?> keimer(@RequestBody SendMailJetEMailRequest emailRequest) {
        SendMailJetEMailCommand sendMailJetEMailCommand = SendMailJetEMailCommand.fromRequest(emailRequest);
        SendMailjetEmailMessage sendMailjetEmailMessage = mediator.send(sendMailJetEMailCommand);
        return ResponseEntity.ok(sendMailjetEmailMessage);

    }
}
