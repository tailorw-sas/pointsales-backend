package com.kynsoft.notification.application.command.campaign.send;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class SendEmailCampaignCommand implements ICommand {
    private final SendCampaignRequest sendCampaignRequest;

    public SendEmailCampaignCommand(SendCampaignRequest sendCampaignRequest) {
        this.sendCampaignRequest = sendCampaignRequest;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
