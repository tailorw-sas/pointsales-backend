package com.kynsoft.notification.application.command.campaign.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.CampaignDto;
import com.kynsoft.notification.domain.dto.TemplateDto;
import com.kynsoft.notification.domain.dto.TenantDto;
import com.kynsoft.notification.domain.dtoEnum.CampaignStatus;
import com.kynsoft.notification.domain.service.CampaignService;
import com.kynsoft.notification.domain.service.ITemplateEntityService;
import com.kynsoft.notification.domain.service.ITenantService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCampaignCommandHandler implements ICommandHandler<CreateCampaignCommand> {

    private final CampaignService campaignService;
    private final ITemplateEntityService templateEntityService;
    private final ITenantService tenantService;

    public CreateCampaignCommandHandler(CampaignService campaignService,
                                        ITemplateEntityService templateEntityService,
                                        ITenantService tenantService) {
        this.campaignService = campaignService;
        this.templateEntityService = templateEntityService;
        this.tenantService = tenantService;
    }

    @Override
    public void handle(CreateCampaignCommand command) {
        CreateCampaignRequest request = command.getCreateCampaignRequest();
        TemplateDto templateDto = templateEntityService.findById(UUID.fromString(request.getTemplateId()));
        TenantDto tenantDto = tenantService.findById(UUID.fromString(request.getTenantId()));
        campaignService.createCampaign(CampaignDto.builder()
                .code(request.getCode())
                .status(CampaignStatus.PENDING)
                .ownerId(UUID.fromString(request.getUserId()))
                .campaignDate(request.getCampaignDate())
                .template(templateDto)
                .tenant(tenantDto)
                .subject(request.getSubject())
                .build()
        );
    }
}
