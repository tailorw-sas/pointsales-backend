package com.kynsof.scheduled.application.command.qualification.delete;

import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandHandler;
import com.kynsof.scheduled.infrastructure.service.QualificationServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeleteQualificationCommandHandler implements ICommandHandler<QualificationDeleteCommand> {

    private final QualificationServiceImpl serviceImpl;

    public DeleteQualificationCommandHandler(QualificationServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(QualificationDeleteCommand command) {

        serviceImpl.delete(command.getId());
    }

}
