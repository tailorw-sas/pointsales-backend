package com.kynsoft.notification.application.command.mailjetConfiguration.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.notification.domain.dto.MailjetConfigurationDto;
import com.kynsoft.notification.domain.dto.TenantDto;
import com.kynsoft.notification.domain.service.IMailjetConfigurationService;
import com.kynsoft.notification.domain.service.ITenantService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

;

@Component
public class CreateMailjetConfigurationCommandHandler implements ICommandHandler<CreateMailjetConfigurationCommand> {

    private final IMailjetConfigurationService mailjetConfigurationService;
    private final ITenantService tenantService;

    public CreateMailjetConfigurationCommandHandler(IMailjetConfigurationService mailjetConfigurationService,
                                                    ITenantService tenantService) {
        this.mailjetConfigurationService = mailjetConfigurationService;
        this.tenantService = tenantService;
    }

    @Override
    public void handle(CreateMailjetConfigurationCommand command) {
        TenantDto tenantDto = command.getTenant() != null ? this.tenantService.findByTenantId(command.getTenant()) : null;

        UUID id = mailjetConfigurationService.create(new MailjetConfigurationDto(
                UUID.randomUUID(),
                command.getMailjetApiSecret(),
                command.getMailjetApiKey(),
                command.getFromEmail(),
                command.getFromName(),
                tenantDto,
                LocalDateTime.now()
        ));
        command.setId(id);
    }
}
