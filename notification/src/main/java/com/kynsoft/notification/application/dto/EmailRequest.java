package com.kynsoft.notification.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class EmailRequest {
    private List<MailJetRecipient> recipientEmail;
    private List<MailJetVar> mailJetVars;
    private List<MailJetAttachment> mailJetAttachments;
    private String subject;
}
