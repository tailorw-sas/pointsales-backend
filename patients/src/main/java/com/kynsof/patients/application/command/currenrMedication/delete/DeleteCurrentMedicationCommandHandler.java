package com.kynsof.patients.application.command.currenrMedication.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.service.ICurrentMedicationService;
import org.springframework.stereotype.Component;

@Component
public class DeleteCurrentMedicationCommandHandler implements ICommandHandler<DeleteCurrentMedicationCommand> {

    private final ICurrentMedicationService serviceImpl;

    public DeleteCurrentMedicationCommandHandler(ICurrentMedicationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteCurrentMedicationCommand command) {

        serviceImpl.delete(command.getId());
    }

}
