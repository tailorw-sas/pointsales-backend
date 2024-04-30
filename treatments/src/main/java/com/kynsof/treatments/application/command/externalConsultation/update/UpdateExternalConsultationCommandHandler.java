package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpdateExternalConsultationCommandHandler implements ICommandHandler<UpdateExternalConsultationCommand> {

    private final IExternalConsultationService serviceImpl;

    public UpdateExternalConsultationCommandHandler(IExternalConsultationService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateExternalConsultationCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "External Consultation ID cannot be null."));
        ExternalConsultationDto update = serviceImpl.findById(command.getId());

        update.setConsultationTime(new Date());
        UpdateIfNotNull.updateIfStringNotNull(update::setConsultationReason, command.getConsultationReason());
        UpdateIfNotNull.updateIfStringNotNull(update::setPhysicalExam, command.getPhysicalExam());
        UpdateIfNotNull.updateIfStringNotNull(update::setMedicalHistory, command.getMedicalHistory());
        UpdateIfNotNull.updateIfStringNotNull(update::setObservations, command.getObservations());
        
        serviceImpl.update(update);

    }
}
