package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class UpdateTreatmentCommandHandler implements ICommandHandler<UpdateTreatmentCommand> {

    private final ITreatmentService serviceImpl;

    public UpdateTreatmentCommandHandler(ITreatmentService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateTreatmentCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Procedure ID cannot be null."));

        TreatmentDto update = this.serviceImpl.findById(command.getId());

        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());
        UpdateIfNotNull.updateIfStringNotNull(update::setDose, command.getDose());
        UpdateIfNotNull.updateIfStringNotNull(update::setDuration, command.getDuration());
        UpdateIfNotNull.updateIfStringNotNull(update::setFrequency, command.getFrequency());
        UpdateIfNotNull.updateIfStringNotNull(update::setMedication, command.getMedication());

        serviceImpl.create(update);
    }
}
