package com.kynsof.patients.application.command.contactInfo.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Patients;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateContactInfoCommandHandler implements ICommandHandler<CreateContactInfoCommand> {

    private final IContactInfoService contactInfoService;
    private final IPatientsService patientsService;
    private final IGeographicLocationService geographicLocationService;

    public CreateContactInfoCommandHandler(IContactInfoService serviceImpl, IPatientsService patientsService, IGeographicLocationService geographicLocationService) {
        this.contactInfoService = serviceImpl;
        this.patientsService = patientsService;
        this.geographicLocationService = geographicLocationService;
    }

    @Override
    public void handle(CreateContactInfoCommand command) {
        PatientDto patientDto = patientsService.findById(command.getPatientId());
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(command.getGeographicLocationId());
        UUID id = contactInfoService.create(new ContactInfoDto(
                UUID.randomUUID(),
                new Patients(patientDto),
                command.getEmail(),
                command.getTelephone(),
                command.getAddress(),
                command.getBirthdayDate(),
                Status.ACTIVE,
                geographicLocationDto
        ));
        command.setId(id);
    }
}
