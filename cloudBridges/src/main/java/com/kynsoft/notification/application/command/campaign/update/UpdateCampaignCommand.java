package com.kynsoft.notification.application.command.campaign.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;

public class UpdateCampaignCommand implements ICommand {

    private final UpdateCampaignRequest updateCampaignRequest;

    public UpdateCampaignCommand(UpdateCampaignRequest updateCampaignRequest) {
        this.updateCampaignRequest = updateCampaignRequest;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
