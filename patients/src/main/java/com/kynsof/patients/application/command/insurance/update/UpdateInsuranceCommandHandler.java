package com.kynsof.patients.application.command.insurance.update;

import com.kynsof.patients.domain.dto.InsuranceDto;
import com.kynsof.patients.domain.service.IInsuranceService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateInsuranceCommandHandler implements ICommandHandler<UpdateInsuranceCommand> {

    private final IInsuranceService insuranceService;

    public UpdateInsuranceCommandHandler(IInsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @Override
    public void handle(UpdateInsuranceCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Insurance ID cannot be null."));

        InsuranceDto update = this.insuranceService.findById(command.getId());

        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());
        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());

        insuranceService.update(update);
    }
}
