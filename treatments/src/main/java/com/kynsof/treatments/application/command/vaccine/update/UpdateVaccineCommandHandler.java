package com.kynsof.treatments.application.command.vaccine.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.rules.vaccine.VaccineNameMustBeUniqueRule;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

@Component
public class UpdateVaccineCommandHandler implements ICommandHandler<UpdateVaccineCommand> {

    private final IVaccineService serviceImpl;

    public UpdateVaccineCommandHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateVaccineCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Vaccine ID cannot be null."));
        VaccineDto _vaccine = this.serviceImpl.findById(command.getId());

        if (UpdateIfNotNull.updateIfStringNotNull(_vaccine::setName, command.getName())) {
            RulesChecker.checkRule(new VaccineNameMustBeUniqueRule(this.serviceImpl, command.getName(), command.getId()));
        }
        UpdateIfNotNull.updateIfStringNotNull(_vaccine::setDescription, command.getDescription());
        UpdateIfNotNull.updateIfStringNotNull(_vaccine::setDose, command.getDose());

        _vaccine.setType(command.getType());
        _vaccine.setMinAge(command.getMinAge());
        _vaccine.setMaxAge(command.getMaxAge());
        _vaccine.setRouteOfAdministration(command.getRouteOfAdministration());
        _vaccine.setPreventableDiseases(command.getPreventableDiseases());
        _vaccine.setServiceId(command.getServiceId());

        serviceImpl.update(_vaccine);
    }
}
