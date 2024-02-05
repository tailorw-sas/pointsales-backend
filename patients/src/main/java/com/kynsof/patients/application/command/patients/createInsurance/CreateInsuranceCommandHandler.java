package com.kynsof.patients.application.command.patients.createInsurance;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.service.IPatientsService;
import org.springframework.stereotype.Component;

@Component
public class CreateInsuranceCommandHandler implements ICommandHandler<CreateInsuranceCommand> {

    private final IPatientsService patientsService;

    public CreateInsuranceCommandHandler(IPatientsService serviceImpl) {
        this.patientsService = serviceImpl;
    }

    @Override
    public void handle(CreateInsuranceCommand command) {

        patientsService.createInsurance(command.getPatientId(), command.getInsuranceIds());
        command.setResult(true);
    }
}
