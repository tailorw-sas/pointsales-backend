package com.kynsof.patients.application.command.contactInfo.update;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.Status;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.stereotype.Component;

@Component
public class UpdateContactInfoCommandHandler implements ICommandHandler<UpdateContactInfoCommand> {

    private final IPatientsService patientsService;
    private final IContactInfoService contactInfoService;

    public UpdateContactInfoCommandHandler(IPatientsService patientsService, IContactInfoService contactInfoService) {
        this.patientsService = patientsService;
        this.contactInfoService = contactInfoService;
    }

    @Override
    public void handle(UpdateContactInfoCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        contactInfoService.update(new ContactInfoDto(
                command.getId(),
                new Patients(patientDto),
                command.getEmail(),
                command.getTelephone(),
                command.getAddress(),
                command.getBirthdayDate(),
                Status.ACTIVE
        ));

    }
}
