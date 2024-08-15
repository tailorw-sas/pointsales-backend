package com.kynsoft.notification.application.command.templateEntity.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.MailjetConfigurationDto;
import com.kynsoft.notification.domain.dto.TemplateDto;
import com.kynsoft.notification.domain.dto.TenantDto;
import com.kynsoft.notification.domain.service.IMailjetConfigurationService;
import com.kynsoft.notification.domain.service.ITemplateEntityService;
import com.kynsoft.notification.domain.service.ITenantService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

;

@Component
public class CreateTemplateEntityCommandHandler implements ICommandHandler<CreateTemplateEntityCommand> {

    private final IMailjetConfigurationService mailjetConfigurationService;
    private final ITemplateEntityService templateEntityService;
    private final ITenantService tenantService;

    public CreateTemplateEntityCommandHandler(IMailjetConfigurationService mailjetConfigurationService,
            ITemplateEntityService templateEntityService,
            ITenantService tenantService) {
        this.mailjetConfigurationService = mailjetConfigurationService;
        this.templateEntityService = templateEntityService;
        this.tenantService = tenantService;
    }

    @Override
    public void handle(CreateTemplateEntityCommand command) {
        MailjetConfigurationDto mailjetConfigurationDto = mailjetConfigurationService.findById(command.getMailjetConfigId());
        TenantDto tenantDto = command.getTenant() != null ? this.tenantService.findByTenantId(command.getTenant()) : null;

        UUID id = templateEntityService.create(new TemplateDto(
                UUID.randomUUID(),
                command.getTemplateCode(),
                command.getName(),
                command.getDescription(),
                mailjetConfigurationDto,
                tenantDto,
                LocalDateTime.now()
        ));
        command.setId(id);
    }
}
