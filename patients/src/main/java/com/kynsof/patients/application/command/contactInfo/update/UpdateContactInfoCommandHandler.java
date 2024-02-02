package com.kynsof.patients.application.command.contactInfo.update;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.infrastructure.entity.Patients;
import com.kynsof.patients.infrastructure.services.ContactInfoServiceImpl;
import com.kynsof.patients.infrastructure.services.PatientsServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UpdateContactInfoCommandHandler implements ICommandHandler<UpdateContactInfoCommand> {

    private final PatientsServiceImpl patientsService;
    private final ContactInfoServiceImpl contactInfoService;

    public UpdateContactInfoCommandHandler(PatientsServiceImpl patientsService, ContactInfoServiceImpl contactInfoService) {
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
                EStatusPatients.ACTIVE
        ));

    }
}
