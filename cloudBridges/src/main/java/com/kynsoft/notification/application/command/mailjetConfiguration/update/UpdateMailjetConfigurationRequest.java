package com.kynsoft.notification.application.command.mailjetConfiguration.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMailjetConfigurationRequest {

    private String Id;
    private String mailjetApiKey;
    private String mailjetApiSecret;
    private String fromEmail;
    private String fromName;
}