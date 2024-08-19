package com.kynsoft.notification.application.command.advertisingcontent.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.utils.ConfigureTimeZone;
import com.kynsoft.notification.domain.dto.AdvertisingContentDto;
import com.kynsoft.notification.domain.dto.TenantDto;
import com.kynsoft.notification.domain.service.IAdvertisingContentService;
import com.kynsoft.notification.domain.service.ITenantService;
import org.springframework.stereotype.Component;

@Component
public class CreateAdvertisingContentCommandHandler implements ICommandHandler<CreateAdvertisingContentCommand> {

    private final IAdvertisingContentService service;
    private final ITenantService tenantService;

    public CreateAdvertisingContentCommandHandler(IAdvertisingContentService service,
                                                  ITenantService tenantService) {
        this.service = service;
        this.tenantService = tenantService;
    }

    @Override
    public void handle(CreateAdvertisingContentCommand command) {
        TenantDto tenantDto = command.getTenant() != null ? this.tenantService.findByTenantId(command.getTenant()) : null;

        this.service.create(new AdvertisingContentDto(
                command.getId(), 
                command.getTitle(), 
                command.getDescription(), 
                command.getType(),
                ConfigureTimeZone.getTimeZone(),
                null,
                command.getImage(),
                command.getLink(),
                tenantDto
        ));
    }
}
