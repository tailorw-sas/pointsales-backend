package com.kynsof.patients.application.command.contactInfo.update;

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

@Component
public class UpdateContactInfoCommandHandler implements ICommandHandler<UpdateContactInfoCommand> {

    private final IPatientsService patientsService;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;

    public UpdateContactInfoCommandHandler(IPatientsService patientsService, IContactInfoService contactInfoService,
                                           IGeographicLocationService geographicLocationService) {
        this.patientsService = patientsService;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
    }

    @Override
    public void handle(UpdateContactInfoCommand command) {
        PatientDto patientDto = patientsService.findByIdSimple(command.getPatientId());
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(command.getGeographicLocationId());
        contactInfoService.update(new ContactInfoDto(
                command.getId(),
                new Patients(patientDto),
                command.getEmail(),
                command.getTelephone(),
                command.getAddress(),
                command.getBirthdayDate(),
                Status.ACTIVE,
                geographicLocationDto));

    }
}
