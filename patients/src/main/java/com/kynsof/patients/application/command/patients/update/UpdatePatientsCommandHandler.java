package com.kynsof.patients.application.command.patients.update;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerUpdatePatientsEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerSaveFileEventService;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientsCommandHandler implements ICommandHandler<UpdatePatientsCommand> {

    private final IPatientsService serviceImpl;
    private final ProducerSaveFileEventService saveFileEventService;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;
    private final ProducerUpdatePatientsEventService updatePatientsEventService;

    public UpdatePatientsCommandHandler(IPatientsService serviceImpl,
                                        ProducerSaveFileEventService saveFileEventService,
                                        IContactInfoService contactInfoService,
                                        IGeographicLocationService geographicLocationService,
                                        ProducerUpdatePatientsEventService updatePatientsEventService) {
        this.serviceImpl = serviceImpl;
        this.saveFileEventService = saveFileEventService;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
        this.updatePatientsEventService = updatePatientsEventService;
    }

    @Override
    public void handle(UpdatePatientsCommand command) {
        ContactInfoDto contactInfoDto = contactInfoService.findByPatientId(command.getId());
        PatientDto patientDto = serviceImpl.findByIdSimple(command.getId());
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(
                command.getCreateContactInfoRequest().getGeographicLocationId());

        patientDto.setIdentification(command.getIdentification());
        patientDto.setName(command.getName());
        patientDto.setLastName(command.getLastName());
        patientDto.setGender(command.getGender());
        patientDto.setWeight(command.getWeight());
        patientDto.setHeight(command.getHeight());
        patientDto.setHasDisability(command.getHasDisability());
        patientDto.setIsPregnant(command.getIsPregnant());
        patientDto.setDisabilityType(command.getDisabilityType());
        patientDto.setGestationTime(command.getGestationTime());
        patientDto.setPhoto(patientDto.getPhoto());
        serviceImpl.update(patientDto);

        if (contactInfoDto.getId() == null) {
            contactInfoDto.setPatient(patientDto);
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

        this.updatePatientsEventService.update(serviceImpl.findByIdSimple(command.getId()),
                command.getCreateContactInfoRequest().getBirthdayDate());
    }
}
