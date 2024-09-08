package com.kynsoft.notification.application.command.campaign.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateCampaignCommand implements ICommand {
    private UUID campaignId;
    private final CreateCampaignRequest createCampaignRequest;

    public CreateCampaignCommand(CreateCampaignRequest createCampaignRequest) {
        this.createCampaignRequest = createCampaignRequest;
    }
    @Override
    public ICommandMessage getMessage() {
        return new CreateCampaignMessage(campaignId);
    }
}
