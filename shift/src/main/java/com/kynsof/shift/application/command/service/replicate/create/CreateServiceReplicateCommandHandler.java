package com.kynsof.shift.application.command.service.replicate.create;

import com.kynsof.shift.domain.dto.ServiceDto;
import com.kynsof.shift.domain.dto.ServiceTypeDto;
import com.kynsof.shift.domain.service.IServiceService;
import com.kynsof.shift.domain.service.IServiceTypeService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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
                serviceTypeDto,
                command.getStatus(),
                command.getImage(),
                command.getName(),
                command.getDescription(),
                command.getCode(),
                command.isPreferFlag(),
                command.getMaxPriorityCount(),
                command.getPriorityCount(),
                command.getCurrentLoop(),
                command.getOrder()
        ));

        command.setId(serviceDto.getId());
    }
}
