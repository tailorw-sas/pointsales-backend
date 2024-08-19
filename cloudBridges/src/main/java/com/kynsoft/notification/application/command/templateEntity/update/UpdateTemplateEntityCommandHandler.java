package com.kynsoft.notification.application.command.templateEntity.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.MailjetConfigurationDto;
import com.kynsoft.notification.domain.dto.TemplateDto;
import com.kynsoft.notification.domain.dto.TenantDto;
import com.kynsoft.notification.domain.service.IMailjetConfigurationService;
import com.kynsoft.notification.domain.service.ITemplateEntityService;
import com.kynsoft.notification.domain.service.ITenantService;
import com.kynsoft.notification.infrastructure.entity.TemplateEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateTemplateEntityCommandHandler implements ICommandHandler<UpdateTemplateEntityCommand> {

    private final IMailjetConfigurationService mailjetConfigurationService;
    private final ITemplateEntityService templateEntityService;
    private final ITenantService tenantService;

    public UpdateTemplateEntityCommandHandler(IMailjetConfigurationService allergyService, 
                                              ITemplateEntityService templateEntityService,
                                              ITenantService tenantService) {
        this.mailjetConfigurationService = allergyService;
        this.templateEntityService = templateEntityService;
        this.tenantService = tenantService;
    }

    @Override
    public void handle(UpdateTemplateEntityCommand command) {
        TemplateDto templateDto = templateEntityService.findById(command.getId());
        MailjetConfigurationDto mailjetConfigurationDto = this.mailjetConfigurationService.findById(command.getMailjetConfigId());
        if (command.getTemplateCode() != null) templateDto.setTemplateCode(command.getTemplateCode());
        if (command.getName() != null) templateDto.setName(command.getName());
        if (command.getDescription() != null) templateDto.setDescription(command.getDescription());
        if (command.getMailjetConfigId() != null) templateDto.setMailjetConfigurationDto(mailjetConfigurationDto);
        if (command.getTenant() != null) {
            TenantDto tenantDto = this.tenantService.findByTenantId(command.getTenant());
            templateDto.setTenant(tenantDto);
        }
        templateEntityService.update(new TemplateEntity(templateDto));
    }
}
