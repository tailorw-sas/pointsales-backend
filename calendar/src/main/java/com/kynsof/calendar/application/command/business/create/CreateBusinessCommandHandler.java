package com.kynsof.calendar.application.command.business.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.utils.FileNameGetExtension;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessCommandHandler implements ICommandHandler<CreateBusinessCommand> {

    private final IBusinessService service;

    public CreateBusinessCommandHandler(IBusinessService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateBusinessCommand command) {
        System.err.println("··················································");
        System.err.println("··················································");
        System.err.println("··················································");
        System.err.println("nombre: " + FileNameGetExtension.getExtension(command.getLogo().getFileName()));
        System.err.println("··················································");
        System.err.println("··················································");
        System.err.println("··················································");
        service.create(new BusinessDto(
                command.getId(),
                command.getName(),
                command.getLatitude(),
                command.getLongitude(),
                command.getDescription(),
                command.getLogo().getFile(),
                command.getRuc()
        ));
    }
}
