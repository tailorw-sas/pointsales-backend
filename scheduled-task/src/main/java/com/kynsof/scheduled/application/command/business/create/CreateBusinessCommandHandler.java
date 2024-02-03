package com.kynsof.scheduled.application.command.business.create;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessCommandHandler implements ICommandHandler<CreateBusinessCommand> {

    private final BusinessServiceImpl serviceImpl;

    public CreateBusinessCommandHandler(BusinessServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateBusinessCommand command) {
        serviceImpl.create(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getLogo(),
                command.getRuc(),
                command.getStatus()
        ));
    }
}
