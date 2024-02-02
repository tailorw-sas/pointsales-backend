package com.kynsof.scheduled.application.command.qualification.update;

import com.kynsof.scheduled.domain.dto.QualificationDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.QualificationServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UpdateQualificationCommandHandler  implements ICommandHandler<UpdateQualificationCommand> {

    private final QualificationServiceImpl serviceImpl;

    public UpdateQualificationCommandHandler(QualificationServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateQualificationCommand command) {
       serviceImpl.update(new QualificationDto(
                command.getId(),
                command.getDescription(),
               command.getStatus()
        ));
    }
}