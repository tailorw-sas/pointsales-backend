package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateDependentPatientsCommandHandler implements ICommandHandler<CreateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;


    public CreateDependentPatientsCommandHandler(IPatientsService serviceImpl, IContactInfoService contactInfoService,
                                                 IGeographicLocationService geographicLocationService
    ) {
        this.serviceImpl = serviceImpl;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
    }

    @Override
    public void handle(CreateDependentPatientsCommand command) {

        PatientDto prime = serviceImpl.findByIdSimple(command.getPrimeId());
        String idLogo = null;
//        if (command.getPhoto() != null) {
//            idLogo = sendFileService.sendImage(command.getName(),command.getPhoto());
//        }
        UUID id = serviceImpl.createDependent(new DependentPatientDto(
                UUID.randomUUID(),
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
                Status.ACTIVE,
                prime,
                command.getWeight(),
                command.getHeight(),
                command.getHasDisability(),
                command.getIsPregnant(),
                command.getFamilyRelationship(),
                idLogo,
                command.getDisabilityType(),
                command.getGestationTime()
        ));
        PatientDto patientDto = serviceImpl.findByIdSimple(id);
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(
                command.getCreateContactInfoRequest().getGeographicLocationId());
        UUID idContactId = contactInfoService.create(new ContactInfoDto(
                UUID.randomUUID(),
                patientDto,
                command.getCreateContactInfoRequest().getEmail(),
                command.getCreateContactInfoRequest().getEmail(),
                command.getCreateContactInfoRequest().getAddress(),
                command.getCreateContactInfoRequest().getBirthdayDate(),
                Status.ACTIVE,
                geographicLocationDto
        ));
        command.setId(id);
    }
}
