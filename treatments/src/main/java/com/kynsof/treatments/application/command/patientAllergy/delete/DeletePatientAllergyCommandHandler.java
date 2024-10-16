package com.kynsof.treatments.application.command.patientAllergy.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.PatientAllergyDto;
import com.kynsof.treatments.domain.service.IPatientAllergyService;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientAllergyCommandHandler implements ICommandHandler<DeletePatientAllergyCommand> {

    private final IPatientAllergyService serviceImpl;

    public DeletePatientAllergyCommandHandler(IPatientAllergyService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeletePatientAllergyCommand command) {
        PatientAllergyDto delete = this.serviceImpl.findById(command.getId());

        serviceImpl.delete(delete);
    }

}
