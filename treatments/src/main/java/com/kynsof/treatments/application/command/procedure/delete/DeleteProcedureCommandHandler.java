package com.kynsof.treatments.application.command.procedure.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.treatments.domain.service.IProcedureService;
import org.springframework.stereotype.Component;

@Component
public class DeleteProcedureCommandHandler implements ICommandHandler<ProcedureDeleteCommand> {

    private final IProcedureService serviceImpl;

    public DeleteProcedureCommandHandler(IProcedureService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(ProcedureDeleteCommand command) {
        ProcedureDto delete = this.serviceImpl.findById(command.getId());

        serviceImpl.delete(delete);
    }

}
