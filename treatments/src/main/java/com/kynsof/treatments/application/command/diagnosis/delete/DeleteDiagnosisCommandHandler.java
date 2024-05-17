package com.kynsof.treatments.application.command.diagnosis.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import org.springframework.stereotype.Component;

@Component
public class DeleteDiagnosisCommandHandler implements ICommandHandler<DiagnosisDeleteCommand> {

    private final IDiagnosisService serviceImpl;

    public DeleteDiagnosisCommandHandler(IDiagnosisService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DiagnosisDeleteCommand command) {
        DiagnosisDto delete = this.serviceImpl.findById(command.getId());

        serviceImpl.delete(delete);
    }

}
