package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class UpdateTreatmentCommandHandler implements ICommandHandler<UpdateTreatmentCommand> {

    private final ITreatmentService serviceImpl;
    private final IExternalConsultationService externalConsultationService;

    public UpdateTreatmentCommandHandler(ITreatmentService serviceImpl, IExternalConsultationService externalConsultationService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
    }

    @Override
    public void handle(UpdateTreatmentCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Procedure ID cannot be null."));

        TreatmentDto update = this.serviceImpl.findById(command.getId());

        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());
        UpdateIfNotNull.updateIfStringNotNull(update::setQuantity, command.getQuantity());
        UpdateIfNotNull.updateIfStringNotNull(update::setDuration, command.getDuration());
        UpdateIfNotNull.updateIfStringNotNull(update::setMedication, command.getMedication());

        try {
            ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
            update.setExternalConsultationDto(externalConsultationDto);
        } catch (Exception e) {
        }
        serviceImpl.create(update);
    }
}
