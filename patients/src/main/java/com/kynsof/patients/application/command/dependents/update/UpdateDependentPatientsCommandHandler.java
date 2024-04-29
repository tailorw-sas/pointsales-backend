package com.kynsof.patients.application.command.dependents.update;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerUpdateDependentPatientsEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

@Component
public class UpdateDependentPatientsCommandHandler implements ICommandHandler<UpdateDependentPatientsCommand> {

    private final IPatientsService serviceImpl;
    private final ProducerSaveFileEventService saveFileEventService;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;
    private final ProducerUpdateDependentPatientsEventService updateDependentPatientsEventService;

    public UpdateDependentPatientsCommandHandler(IPatientsService serviceImpl,
                                                 ProducerSaveFileEventService saveFileEventService,
                                                 IContactInfoService contactInfoService,
                                                 IGeographicLocationService geographicLocationService,
                                                 ProducerUpdateDependentPatientsEventService updateDependentPatientsEventService) {
        this.serviceImpl = serviceImpl;
        this.saveFileEventService = saveFileEventService;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
        this.updateDependentPatientsEventService = updateDependentPatientsEventService;
    }

    @Override
    public void handle(UpdateDependentPatientsCommand command) {
        ContactInfoDto contactInfoDto = contactInfoService.findByPatientId(command.getId());
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(command.getCreateContactInfoRequest().getGeographicLocationId());
        PatientDto prime = serviceImpl.findByIdSimple(command.getPrimeId());
        PatientDto dependent = serviceImpl.findByIdSimple(command.getId());

        serviceImpl.updateDependent(new DependentPatientDto(
                command.getId(),
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

        if (contactInfoDto.getEmail() == null) {
            contactInfoDto.setPatient(dependent);
            contactInfoDto.setAddress(command.getCreateContactInfoRequest().getAddress());
            contactInfoDto.setTelephone(command.getCreateContactInfoRequest().getTelephone());
            contactInfoDto.setBirthdayDate(command.getCreateContactInfoRequest().getBirthdayDate());
            contactInfoDto.setEmail(command.getCreateContactInfoRequest().getEmail());
            contactInfoDto.setGeographicLocation(geographicLocationDto);
            contactInfoDto.setStatus(Status.ACTIVE);
            contactInfoService.create(contactInfoDto);
        } else {
            contactInfoDto.setAddress(command.getCreateContactInfoRequest().getAddress());
            contactInfoDto.setTelephone(command.getCreateContactInfoRequest().getTelephone());
            contactInfoDto.setBirthdayDate(command.getCreateContactInfoRequest().getBirthdayDate());
            contactInfoDto.setGeographicLocation(geographicLocationDto);
            contactInfoDto.setEmail(command.getCreateContactInfoRequest().getEmail());
            contactInfoDto.setStatus(Status.ACTIVE);
            contactInfoService.update(contactInfoDto);
        }

        this.updateDependentPatientsEventService.update(serviceImpl.findByIdSimple(command.getId()), command.getCreateContactInfoRequest().getBirthdayDate());
    }
}
