package com.kynsof.calendar.application.command.serviceType.update;

import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateServiceTypeCommandHandler implements ICommandHandler<UpdateServiceTypeCommand> {

    private final IServiceTypeService service;

    public UpdateServiceTypeCommandHandler(IServiceTypeService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateServiceTypeCommand command) {
       service.update(new ServiceTypeDto(
                command.getId(),
                command.getName()
        ));
    }
}