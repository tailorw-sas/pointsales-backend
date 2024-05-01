package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.application.command.treatment.create.CreateAllTreatmentRequest;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.domain.service.IMedicinesService;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        ExternalConsultationDto externalConsultationDto = this.externalConsultationService.findById(command.getIdExternalConsultation());
        List<TreatmentDto> treatmentDtoList = externalConsultationDto.getTreatments();
        externalConsultationDto.setTreatments(new ArrayList<>());
        serviceImpl.deleteByIds(treatmentDtoList.stream().map(TreatmentDto::getId).toList());
        externalConsultationService.update(externalConsultationDto);

        for (CreateAllTreatmentRequest createTreatmentRequest : command.getPayload()) {
            MedicinesDto medicinesDto = this.medicinesService.findById(UUID.fromString(createTreatmentRequest.getMedication()));
            TreatmentDto create = new TreatmentDto(
                    UUID.randomUUID(),
                    createTreatmentRequest.getDescription(),
                    medicinesDto,
                    createTreatmentRequest.getQuantity(),
                    createTreatmentRequest.getMedicineUnit()
            );
            create.setExternalConsultationDto(externalConsultationDto);
            serviceImpl.create(create);
        }

    }
}
