package com.kynsof.treatments.application.command.procedure.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ProcedureDto;
import com.kynsof.treatments.domain.service.IProcedureService;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureCommandHandler  implements ICommandHandler<CreateProcedureCommand> {

    private final IProcedureService serviceImpl;

    public CreateProcedureCommandHandler(IProcedureService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateProcedureCommand command) {
       serviceImpl.create(new ProcedureDto(command.getId(), command.getCode(), command.getName(), command.getDescription(), command.getType(), command.getPrice()));
    }
}
