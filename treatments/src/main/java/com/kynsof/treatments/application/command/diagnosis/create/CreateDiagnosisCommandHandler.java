package com.kynsof.treatments.application.command.diagnosis.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import org.springframework.stereotype.Component;

@Component
public class CreateDiagnosisCommandHandler implements ICommandHandler<CreateDiagnosisCommand> {

    private final IDiagnosisService serviceImpl;

    public CreateDiagnosisCommandHandler(IDiagnosisService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateDiagnosisCommand command) {
        serviceImpl.create(new DiagnosisDto(command.getId(), command.getIcdCode(), command.getDescription()));
    }
}
