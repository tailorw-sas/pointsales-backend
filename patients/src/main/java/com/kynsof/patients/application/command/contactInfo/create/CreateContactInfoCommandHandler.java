package com.kynsof.patients.application.command.contactInfo.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerCreateContactEventService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateContactInfoCommandHandler implements ICommandHandler<CreateContactInfoCommand> {

    private final IContactInfoService contactInfoService;
    private final IPatientsService patientsService;
    private final IGeographicLocationService geographicLocationService;
    private final ProducerCreateContactEventService producerCreateContactEventService;

    public CreateContactInfoCommandHandler(IContactInfoService serviceImpl, IPatientsService patientsService, 
            IGeographicLocationService geographicLocationService, ProducerCreateContactEventService producerCreateContactEventService) {
        this.contactInfoService = serviceImpl;
        this.patientsService = patientsService;
        this.geographicLocationService = geographicLocationService;
        this.producerCreateContactEventService = producerCreateContactEventService;
    }

    @Override
    public void handle(CreateContactInfoCommand command) {
        PatientDto patientDto = patientsService.findByIdSimple(command.getPatientId());
        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(command.getGeographicLocationId());

        ContactInfoDto contactInfoDto = contactInfoService.findByPatientId(command.getPatientId());

        if (contactInfoDto == null) {
            ContactInfoDto create = new ContactInfoDto(
                    UUID.randomUUID(),
                    patientDto,
                    "",
                    command.getTelephone(),
                    command.getAddress(),
                    command.getBirthdayDate(),
                    Status.ACTIVE,
                    geographicLocationDto
            );
            UUID id = contactInfoService.create(create);
            command.setId(id);
            this.producerCreateContactEventService.create(create);
        } else {
            contactInfoDto.setAddress(command.getAddress());
            contactInfoDto.setBirthdayDate(command.getBirthdayDate());
            contactInfoDto.setTelephone(command.getTelephone());
            contactInfoDto.setStatus(Status.ACTIVE);
            contactInfoService.update(contactInfoDto);
            command.setId(contactInfoDto.getId());
            this.producerCreateContactEventService.create(contactInfoDto);
        }
        //TODO yannier Evento de confirmación de creacion de usuario y datos de como debe acceder, se envia un correo
    }
}
