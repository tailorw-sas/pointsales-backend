package com.kynsof.patients.application.command.currenrMedication.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.CurrentMerdicationEntityDto;
import com.kynsof.patients.domain.service.ICurrentMedicationService;
import com.kynsof.patients.infrastructure.entity.CurrentMedication;
import org.springframework.stereotype.Component;

@Component
public class UpdateCurrentMedicationCommandHandler implements ICommandHandler<UpdateCurrentMedicationCommand> {

    private final ICurrentMedicationService currentMedicationService;

    public UpdateCurrentMedicationCommandHandler(ICurrentMedicationService currentMedicationService) {
        this.currentMedicationService = currentMedicationService;
    }

    @Override
    public void handle(UpdateCurrentMedicationCommand command) {

        CurrentMerdicationEntityDto allergyEntityDto = this.currentMedicationService.findById(command.getId());
        if (command.getDosage() != null) allergyEntityDto.setDosage(command.getDosage());
        if (command.getName() != null) allergyEntityDto.setName(command.getName());
        if (command.getStatus() != null) allergyEntityDto.setStatus(command.getStatus());
        currentMedicationService.update(new CurrentMedication(allergyEntityDto));
    }
}
