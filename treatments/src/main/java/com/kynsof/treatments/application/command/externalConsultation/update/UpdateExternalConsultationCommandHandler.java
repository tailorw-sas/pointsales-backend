package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
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
        ExternalConsultationDto externalConsultationDto = serviceImpl.findById(command.getId());

        externalConsultationDto.setConsultationTime(new Date());
        externalConsultationDto.setConsultationReason(command.getConsultationReason());
        externalConsultationDto.setPhysicalExam(command.getPhysicalExam());
        externalConsultationDto.setMedicalHistory(command.getMedicalHistory());
        externalConsultationDto.setMedicalHistory(command.getObservations());
        externalConsultationDto.setObservations(command.getObservations());
        serviceImpl.update(new ExternalConsultation(
                externalConsultationDto
        ));

    }
}
