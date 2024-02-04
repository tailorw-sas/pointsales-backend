package com.kynsof.patients.application.command.allergy.update;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.AllergyEntityDto;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IAllergyService;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Allergy;
import com.kynsof.patients.infrastructure.entity.Patients;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UpdateAllergyCommandHandler implements ICommandHandler<UpdateAllergyCommand> {

    private final IAllergyService allergyService;

    public UpdateAllergyCommandHandler(IAllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @Override
    public void handle(UpdateAllergyCommand command) {

        AllergyEntityDto allergyEntityDto = this.allergyService.findById(command.getId());
        if (command.getCode() != null) allergyEntityDto.setCode(command.getCode());
        if (command.getName() != null) allergyEntityDto.setName(command.getName());
        if (command.getStatus() != null) allergyEntityDto.setStatus(command.getStatus());
        allergyService.update(new Allergy(allergyEntityDto));
    }
}
