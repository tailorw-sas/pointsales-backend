package com.kynsof.scheduled.application.command.qualification.create;

import com.kynsof.scheduled.domain.dto.QualificationDto;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.QualificationServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateQualificationCommandHandler  implements ICommandHandler<CreateQualificationCommand> {

    private final QualificationServiceImpl serviceImpl;

    public CreateQualificationCommandHandler(QualificationServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateQualificationCommand command) {
       serviceImpl.create(new QualificationDto(
                command.getId(),
                command.getDescription()
        ));
    }
}
