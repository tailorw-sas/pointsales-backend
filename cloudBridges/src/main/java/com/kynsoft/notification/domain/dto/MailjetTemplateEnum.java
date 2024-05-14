package com.kynsoft.notification.domain.dto;

import lombok.Getter;

@Getter
public enum MailjetTemplateEnum {

    OTP(5964805),
    EMAIL_CONFIRMATION(22222);


    private final int templateId;

    MailjetTemplateEnum(int templateId) {
        this.templateId = templateId;
    }
}
