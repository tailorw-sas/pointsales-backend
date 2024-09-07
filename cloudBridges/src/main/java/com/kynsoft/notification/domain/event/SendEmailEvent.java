package com.kynsoft.notification.domain.event;

import lombok.Getter;

@Getter
public class SendEmailEvent {
    private final String camapaignId;

    public SendEmailEvent(String camapaignId) {
        this.camapaignId = camapaignId;
    }
}
