package com.kynsof.scheduled.application.command.business.update;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.service.IBusinessService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessCommandHandler  implements ICommandHandler<UpdateBusinessCommand> {

    private final IBusinessService service;

    public UpdateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateBusinessCommand command) {
       service.update(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getLogo(),
                command.getRuc(),
                command.getStatus()
        ));
    }
}