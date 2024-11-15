package com.kynsof.treatments.application.command.services.replicate.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ServiceDto;
import com.kynsof.treatments.domain.dto.ServiceTypeDto;
import com.kynsof.treatments.domain.service.IServiceService;
import com.kynsof.treatments.domain.service.IServiceTypeService;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceReplicateCommandHandler implements ICommandHandler<CreateServiceReplicateCommand> {

    private final IServiceService service;
    private final IServiceTypeService serviceTypeService;

    public CreateServiceReplicateCommandHandler(IServiceService service, IServiceTypeService serviceTypeService) {
        this.service = service;
        this.serviceTypeService = serviceTypeService;
    }

    @Override
    public void handle(CreateServiceReplicateCommand command) {
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(command.getType());

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
