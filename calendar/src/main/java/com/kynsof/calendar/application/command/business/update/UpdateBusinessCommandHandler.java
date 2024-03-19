package com.kynsof.calendar.application.command.business.update;

import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessCommandHandler  implements ICommandHandler<UpdateBusinessCommand> {

    private final IBusinessService service;

    public UpdateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateBusinessCommand command) {
//       service.update(new BusinessDto(
//                command.getId(),
//                command.getName(),
//                command.getLatitude(),
//                command.getLongitude(),
//                command.getDescription(),
//                UUID.randomUUID(),
//                command.getRuc(),
//                command.getStatus()
//        ));
    }
}