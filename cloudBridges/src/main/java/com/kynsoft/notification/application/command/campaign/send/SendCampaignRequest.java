package com.kynsoft.notification.application.command.campaign.send;

import lombok.Getter;

@Getter
public class SendCampaignRequest {
    private final String campaignId;

    public SendCampaignRequest(String campaignId) {
        this.campaignId = campaignId;
    }
}
