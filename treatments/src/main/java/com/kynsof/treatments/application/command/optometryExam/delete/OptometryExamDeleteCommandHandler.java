package com.kynsof.treatments.application.command.optometryExam.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.service.IOptometryExamService;
import org.springframework.stereotype.Component;

@Component
public class OptometryExamDeleteCommandHandler implements ICommandHandler<OptometryExamDeleteCommand> {

    private final IOptometryExamService service;

    public OptometryExamDeleteCommandHandler(IOptometryExamService service) {
        this.service = service;
    }

    @Override
    public void handle(OptometryExamDeleteCommand command) {
        service.delete(command.getId());
    }
}