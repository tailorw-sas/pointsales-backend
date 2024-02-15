package com.kynsof.scheduled.application.command.qualification.update;

import com.kynsof.scheduled.domain.dto.QualificationDto;
import com.kynsof.scheduled.domain.service.IQualificationService;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateQualificationCommandHandler  implements ICommandHandler<UpdateQualificationCommand> {

    private final IQualificationService service;

    public UpdateQualificationCommandHandler(IQualificationService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateQualificationCommand command) {
       service.update(new QualificationDto(
                command.getId(),
                command.getDescription(),
               command.getStatus()
        ));
    }
}