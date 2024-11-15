package com.kynsof.treatments.application.command.services.replicate.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ServiceDto;
import com.kynsof.treatments.domain.service.IServiceService;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceReplicateCommandHandler implements ICommandHandler<CreateServiceReplicateCommand> {

    private final IServiceService service;


    public CreateServiceReplicateCommandHandler(IServiceService service) {
        this.service = service;

    }

    @Override
    public void handle(CreateServiceReplicateCommand command) {

        ServiceDto serviceDto = service.create(new ServiceDto(
                command.getId(),
                command.getStatus(),
                command.getImage(),
                command.getName(),
                command.getDescription(),
                command.getCode()
        ));

        command.setId(serviceDto.getId());
    }
}
