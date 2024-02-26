package com.kynsof.calendar.application.command.qualification.delete;

import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteQualificationCommandHandler implements ICommandHandler<QualificationDeleteCommand> {

    private final IQualificationService service;

    public DeleteQualificationCommandHandler(IQualificationService service) {
        this.service = service;
    }

    @Override
    public void handle(QualificationDeleteCommand command) {

        service.delete(command.getId());
    }

}
