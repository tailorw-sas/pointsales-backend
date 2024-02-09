package com.kynsof.patients.application.command.contactInfo.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateContactInfoCommandHandler implements ICommandHandler<CreateContactInfoCommand> {

    private final IContactInfoService contactInfoService;
    private final IPatientsService patientsService;

    public CreateContactInfoCommandHandler(IContactInfoService serviceImpl, IPatientsService patientsService) {
        this.contactInfoService = serviceImpl;
        this.patientsService = patientsService;
    }

    @Override
    public void handle(CreateContactInfoCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        UUID id = contactInfoService.create(new ContactInfoDto(
                UUID.randomUUID(),
                new Patients(patientDto),
                command.getEmail(),
                command.getTelephone(),
                command.getAddress(),
                command.getBirthdayDate(),
                Status.ACTIVE
        ));
        command.setId(id);
    }
}
