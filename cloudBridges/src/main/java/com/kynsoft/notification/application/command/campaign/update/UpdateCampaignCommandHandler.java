package com.kynsoft.notification.application.command.campaign.update;

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
public class UpdateCampaignCommandHandler implements ICommandHandler<UpdateCampaignCommand> {

    private final CampaignService campaignService;
    private final ITenantService tenantService;
    private final ITemplateEntityService templateEntityService;

    public UpdateCampaignCommandHandler(CampaignService campaignService, ITenantService tenantService, ITemplateEntityService templateEntityService) {
        this.campaignService = campaignService;
        this.tenantService = tenantService;
        this.templateEntityService = templateEntityService;
    }

    @Override
    public void handle(UpdateCampaignCommand command) {
        UpdateCampaignRequest request = command.getUpdateCampaignRequest();
        TenantDto tenantDto = tenantService.findById(UUID.fromString(request.getTenantId()));
        TemplateDto templateDto = templateEntityService.findById(UUID.fromString(request.getTemplateId()));
        CampaignDto campaignDto =campaignService.getCampaignById(request.getId());
        campaignDto.setCode(request.getCode());
        campaignDto.setCampaignDate(request.getCampaignDate());
        campaignDto.setTemplate(templateDto);
        campaignDto.setTenant(tenantDto);
        campaignDto.setAmountEmailOpen(request.getAmountEmailOpen());
        campaignDto.setAmountEmailSent(request.getAmountEmailSent());
        campaignDto.setSubject(request.getSubject());
        campaignDto.setStatus(CampaignStatus.valueOf(request.getStatus()));
        campaignService.updateCampaign(campaignDto);
    }
}
