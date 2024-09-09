package com.kynsoft.notification.application.command.campaign.send;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.service.CampaignService;
import org.springframework.stereotype.Component;

@Component
public class SendEmailCampaignCommandHandler implements ICommandHandler<SendEmailCampaignCommand> {
    private final CampaignService campaignService;

    public SendEmailCampaignCommandHandler(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    public void handle(SendEmailCampaignCommand command) {
        campaignService.sendEmailCampaign(command.getSendCampaignRequest().getCampaignId());
    }
}
