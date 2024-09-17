package com.kynsof.patients.application.command.patients.update;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.customer.ProducerCreateCustomerEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.CustomerKafka;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientsCommandHandler implements ICommandHandler<UpdatePatientsCommand> {

    private final IPatientsService serviceImpl;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;
    private final ProducerCreateCustomerEventService createCustomerEventService;

    public UpdatePatientsCommandHandler(IPatientsService serviceImpl,
                                        IContactInfoService contactInfoService,
                                        IGeographicLocationService geographicLocationService,
                                        ProducerCreateCustomerEventService createCustomerEventService) {
        this.serviceImpl = serviceImpl;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
        this.createCustomerEventService = createCustomerEventService;
    }

    @Override
    public void handle(UpdatePatientsCommand command) {
        ContactInfoDto contactInfoDto = contactInfoService.findByPatientId(command.getId());
        PatientDto patientDto = serviceImpl.findByIdSimple(command.getId());
        GeographicLocationDto parroquia = geographicLocationService.findById(command.getCreateContactInfoRequest().getParroquia());

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
        patientDto.setPhoto(command.getPhoto());
        serviceImpl.update(patientDto);

        if (contactInfoDto.getId() == null) {
            contactInfoDto.setPatient(patientDto);
            contactInfoDto.setAddress(command.getCreateContactInfoRequest().getAddress());
            contactInfoDto.setTelephone(command.getCreateContactInfoRequest().getTelephone());
            contactInfoDto.setBirthdayDate(command.getCreateContactInfoRequest().getBirthdayDate());
            contactInfoDto.setEmail(command.getCreateContactInfoRequest().getEmail());
            contactInfoDto.setParroquia(parroquia);
            contactInfoDto.setStatus(Status.ACTIVE);
            contactInfoService.create(contactInfoDto);
        } else {
            contactInfoDto.setAddress(command.getCreateContactInfoRequest().getAddress());
            contactInfoDto.setTelephone(command.getCreateContactInfoRequest().getTelephone());
            contactInfoDto.setBirthdayDate(command.getCreateContactInfoRequest().getBirthdayDate());
            contactInfoDto.setParroquia(parroquia);
            contactInfoDto.setEmail(command.getCreateContactInfoRequest().getEmail());
            contactInfoDto.setStatus(Status.ACTIVE);
            contactInfoService.update(contactInfoDto);
        }

//        this.updatePatientsEventService.update(serviceImpl.findByIdSimple(command.getId()),
//                command.getCreateContactInfoRequest().getBirthdayDate());
//        this.updateCustomerEventService.update(new UpdateCustomerKafka(
//                patientDto.getId().toString(), 
//                patientDto.getName(), 
//                patientDto.getLastName()
//        ));
        this.createCustomerEventService.create(new CustomerKafka(
                patientDto.getId().toString(), 
                patientDto.getIdentification(), 
                patientDto.getName(), 
                patientDto.getLastName(), 
                contactInfoDto.getEmail(), 
                patientDto.getPhoto(), 
                contactInfoDto.getBirthdayDate(),
                patientDto.getGender().name()
        ));
    }
}
