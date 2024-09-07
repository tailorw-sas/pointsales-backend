package com.kynsoft.notification.application.command.campaign.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.CampaignDto;
import com.kynsoft.notification.domain.dtoEnum.CampaignStatus;
import com.kynsoft.notification.domain.service.CampaignService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCampaignCommandHandler implements ICommandHandler<CreateCampaignCommand> {

    private final CampaignService campaignService;

    public CreateCampaignCommandHandler(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    public void handle(CreateCampaignCommand command) {
        CreateCampaignRequest request= command.getCreateCampaignRequest();
        campaignService.createCampaign(CampaignDto.builder()
                .code(request.getCode())
                        .status(CampaignStatus.PENDING)
                        .ownerId(UUID.fromString(request.getUserId()))
                        .campaignDate(request.getCampaignDate())
                .build()
        );
    }
}
