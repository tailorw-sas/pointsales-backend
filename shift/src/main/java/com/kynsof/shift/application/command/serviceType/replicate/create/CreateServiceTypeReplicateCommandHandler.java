package com.kynsof.shift.application.command.serviceType.replicate.create;

import com.kynsof.shift.domain.dto.ServiceTypeDto;
import com.kynsof.shift.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateServiceTypeReplicateCommandHandler implements ICommandHandler<CreateServiceTypeReplicateCommand> {

    private final IServiceTypeService service;

    public CreateServiceTypeReplicateCommandHandler(IServiceTypeService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateServiceTypeReplicateCommand command) {

        UUID id = service.create(new ServiceTypeDto(
                command.getId(),
                command.getName(),
                command.getPicture(),
                command.getStatus(),
                command.getCode()
        ));
        command.setId(id);
    }
}
