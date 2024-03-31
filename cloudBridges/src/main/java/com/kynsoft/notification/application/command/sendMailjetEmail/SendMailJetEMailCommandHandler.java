package com.kynsoft.notification.application.command.sendMailjetEmail;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.EmailRequest;
import com.kynsoft.notification.domain.service.IEmailService;
import org.springframework.stereotype.Component;

;import java.util.ArrayList;

@Component
public class SendMailJetEMailCommandHandler implements ICommandHandler<SendMailJetEMailCommand> {

    private final IEmailService service;


    public SendMailJetEMailCommandHandler(IEmailService service) {
        this.service = service;

    }

    @Override
    public void handle(SendMailJetEMailCommand command) {

        EmailRequest emailRequest = new EmailRequest(command.getRecipientEmail(), command.getMailJetVars(),
                new ArrayList<>(), command.getSubject(), command.getTemplateId());
        try {
            this.service.sendEmailMailjet(emailRequest);

        } catch (Exception ignored) {

        }
    }
}
