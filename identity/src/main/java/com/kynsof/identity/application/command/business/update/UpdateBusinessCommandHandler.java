package com.kynsof.identity.application.command.business.update;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateBusinessCommandHandler  implements ICommandHandler<UpdateBusinessCommand> {

    private final IBusinessService service;

    public UpdateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateBusinessCommand command) {
        UUID logoId = UUID.randomUUID();
       service.update(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getLatitude(),
                command.getLongitude(),
                command.getDescription(),
                logoId.toString(),
                command.getRuc(),
                command.getStatus()
        ));
    }
}