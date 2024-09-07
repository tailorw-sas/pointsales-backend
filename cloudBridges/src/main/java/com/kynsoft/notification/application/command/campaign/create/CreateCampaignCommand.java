package com.kynsoft.notification.application.command.campaign.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateCampaignCommand implements ICommand {

    private final CreateCampaignRequest createCampaignRequest;

    public CreateCampaignCommand(CreateCampaignRequest createCampaignRequest) {
        this.createCampaignRequest = createCampaignRequest;
    }
    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
