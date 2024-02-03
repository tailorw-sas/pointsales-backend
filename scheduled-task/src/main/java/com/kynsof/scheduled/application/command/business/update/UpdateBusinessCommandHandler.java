package com.kynsof.scheduled.application.command.business.update;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.BusinessServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessCommandHandler  implements ICommandHandler<UpdateBusinessCommand> {

    private final BusinessServiceImpl serviceImpl;

    public UpdateBusinessCommandHandler(BusinessServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateBusinessCommand command) {
       serviceImpl.update(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getLogo(),
                command.getRuc(),
                command.getStatus()
        ));
    }
}