package com.kynsoft.notification.application.query.mailjetConfiguration.getById;


import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.notification.domain.dto.MailjetConfigurationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class MailjetConfigurationResponse implements IResponse {
    private UUID id;
    private String mailjetApiKey;
    private String mailjetApiSecret;
    private String fromEmail;
    private String fromName;

    public MailjetConfigurationResponse(MailjetConfigurationDto patients) {
        this.id = patients.getId();
        mailjetApiKey = patients.getMailjetApiKey();
        mailjetApiSecret = patients.getMailjetApiSecret();
        fromEmail = patients.getFromEmail();
        fromName = patients.getFromName();
    }

}