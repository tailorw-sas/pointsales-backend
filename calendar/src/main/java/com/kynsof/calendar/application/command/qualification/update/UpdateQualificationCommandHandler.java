package com.kynsof.calendar.application.command.qualification.update;

import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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