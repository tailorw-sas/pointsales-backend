package com.kynsof.patients.application.command.contactInfo.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.infrastructure.entity.Patients;
import com.kynsof.patients.infrastructure.services.ContactInfoServiceImpl;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateContactInfoCommandHandler implements ICommandHandler<CreateContactInfoCommand> {

    private final ContactInfoServiceImpl contactInfoService;
    private final PatientsServiceImpl patientsService;

    public CreateContactInfoCommandHandler(ContactInfoServiceImpl serviceImpl, PatientsServiceImpl patientsService) {
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
                EStatusPatients.ACTIVE
        ));
        command.setId(id);
    }
}
