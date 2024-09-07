package com.kynsoft.notification.application.command.campaign.update;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UpdateCampaignRequest {
    private final String id;
    private final String userId;
    private final String code;
    private final String status;
    private final LocalDate campaignDate;
    private final String templateId;
    private final String tenantId;
    private final long amountEmailSent;
    private final long amountEmailOpen;
    private final String subject;
}
