package com.kynsof.patients.application.command.insurance.create;

import com.kynsof.patients.domain.dto.InsuranceDto;
import com.kynsof.patients.domain.service.IInsuranceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateNewInsuranceCommandHandler implements ICommandHandler<CreateNewInsuranceCommand> {

    private final IInsuranceService insuranceService;

    public CreateNewInsuranceCommandHandler(IInsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @Override
    public void handle(CreateNewInsuranceCommand command) {
        insuranceService.create(new InsuranceDto(command.getId(), command.getName(), command.getDescription()));
    }
}
