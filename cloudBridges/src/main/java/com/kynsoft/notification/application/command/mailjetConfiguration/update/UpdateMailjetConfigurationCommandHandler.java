package com.kynsoft.notification.application.command.mailjetConfiguration.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.MailjetConfigurationDto;
import com.kynsoft.notification.domain.dto.TenantDto;
import com.kynsoft.notification.domain.service.IMailjetConfigurationService;
import com.kynsoft.notification.domain.service.ITenantService;
import com.kynsoft.notification.infrastructure.entity.MailjetConfiguration;
import org.springframework.stereotype.Component;

@Component
public class UpdateMailjetConfigurationCommandHandler implements ICommandHandler<UpdateMailjetConfigurationCommand> {

    private final IMailjetConfigurationService mailjetConfigurationService;
    private final ITenantService tenantService;

    public UpdateMailjetConfigurationCommandHandler(IMailjetConfigurationService allergyService,
                                                    ITenantService tenantService) {
        this.mailjetConfigurationService = allergyService;
        this.tenantService = tenantService;
    }

    @Override
    public void handle(UpdateMailjetConfigurationCommand command) {

        MailjetConfigurationDto mailjetConfigurationDto = this.mailjetConfigurationService.findById(command.getId());
        if (command.getMailjetApiKey() != null) mailjetConfigurationDto.setMailjetApiKey(command.getMailjetApiKey());
        if (command.getMailjetApiSecret() != null) mailjetConfigurationDto.setMailjetApiSecret(command.getMailjetApiSecret());
        if (command.getFromEmail() != null) mailjetConfigurationDto.setFromEmail(command.getFromEmail());
        if (command.getFromName() != null) mailjetConfigurationDto.setFromName(command.getFromName());
        if (command.getTenant() != null) {
            TenantDto tenantDto = this.tenantService.findByTenantId(command.getTenant());
            mailjetConfigurationDto.setTenant(tenantDto);
        }
        mailjetConfigurationService.update(new MailjetConfiguration(mailjetConfigurationDto));
    }
}
