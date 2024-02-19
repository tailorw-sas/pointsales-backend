package com.kynsof.treatments.application.command.patientVaccine.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.infrastructure.service.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientVaccineCommandHandler implements ICommandHandler<PatientsPatientVaccineCommand> {

    private final PatientsServiceImpl serviceImpl;

    public DeletePatientVaccineCommandHandler(PatientsServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(PatientsPatientVaccineCommand command) {

        serviceImpl.delete(command.getId());
    }

}
