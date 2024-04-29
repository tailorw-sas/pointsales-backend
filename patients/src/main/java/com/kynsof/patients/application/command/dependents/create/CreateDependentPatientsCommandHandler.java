package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.rules.dependent.DependentMustBeUniqueRule;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerCreateDependentPatientsEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateDependentPatientsCommandHandler implements ICommandHandler<CreateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;
    private final ProducerSaveFileEventService saveFileEventService;

    private ProducerCreateDependentPatientsEventService dependentPatientsEventService;

    public CreateDependentPatientsCommandHandler(IPatientsService serviceImpl, IContactInfoService contactInfoService,
                                                 IGeographicLocationService geographicLocationService, ProducerSaveFileEventService saveFileEventService, ProducerCreateDependentPatientsEventService dependentPatientsEventService
    ) {
        this.serviceImpl = serviceImpl;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
        this.saveFileEventService = saveFileEventService;
        this.dependentPatientsEventService = dependentPatientsEventService;
    }

    @Override
    public void handle(CreateDependentPatientsCommand command) {

        PatientDto prime = serviceImpl.findByIdSimple(command.getPrimeId());


        UUID idDependent = UUID.randomUUID();
        RulesChecker.checkRule(new DependentMustBeUniqueRule(this.serviceImpl, command.getIdentification(), idDependent));
        UUID id = serviceImpl.createDependent(new DependentPatientDto(
                idDependent,
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
                command.getPhoto(),
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
                command.getCreateContactInfoRequest().getTelephone(),
                command.getCreateContactInfoRequest().getAddress(),
                command.getCreateContactInfoRequest().getBirthdayDate(),
                Status.ACTIVE,
                geographicLocationDto
        ));
        command.setId(id);
        this.dependentPatientsEventService.create(patientDto, command.getCreateContactInfoRequest().getBirthdayDate());
    }
}
