package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IMedicinesService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UpdateTreatmentCommandHandler implements ICommandHandler<UpdateTreatmentCommand> {

    private final ITreatmentService serviceImpl;
    private final IExternalConsultationService externalConsultationService;
    private final IMedicinesService medicinesService;

    public UpdateTreatmentCommandHandler(ITreatmentService serviceImpl, IExternalConsultationService externalConsultationService, IMedicinesService medicinesService) {
        this.serviceImpl = serviceImpl;
        this.externalConsultationService = externalConsultationService;
        this.medicinesService = medicinesService;
    }

    @Override
    public void handle(UpdateTreatmentCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Procedure ID cannot be null."));

        TreatmentDto update = this.serviceImpl.findById(command.getId());

        UpdateIfNotNull.updateIfStringNotNull(update::setDescription, command.getDescription());

        MedicinesDto medicinesDto = this.medicinesService.findById(UUID.fromString(command.getMedication()));
        update.setMedication(medicinesDto);
        try {
            ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
            update.setExternalConsultationDto(externalConsultationDto);
        } catch (Exception e) {
        }
        serviceImpl.create(update);
    }
}
