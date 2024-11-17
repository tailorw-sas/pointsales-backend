package com.kynsof.treatments.application.command.businessProcedure.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.service.IBusinessProcedureService;
import org.springframework.stereotype.Component;

@Component
public class DeleteBusinessProcedureCommandHandler implements ICommandHandler<DeleteBusinessProcedureCommand> {

    private final IBusinessProcedureService serviceImpl;

    public DeleteBusinessProcedureCommandHandler(IBusinessProcedureService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteBusinessProcedureCommand command) {
        serviceImpl.delete(command.getId());
    }

}
