package com.kynsoft.notification.application.command.campaign.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCampaignMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_CAMPAIGN";

    public CreateCampaignMessage(UUID id) {
        this.id = id;
    }

}
