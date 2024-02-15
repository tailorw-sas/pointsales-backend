package com.kynsof.scheduled.application.command.business.create;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.service.IBusinessService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessCommandHandler implements ICommandHandler<CreateBusinessCommand> {

    private final IBusinessService service;

    public CreateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateBusinessCommand command) {
        service.create(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getLogo(),
                command.getRuc(),
                command.getStatus()
        ));
    }
}
