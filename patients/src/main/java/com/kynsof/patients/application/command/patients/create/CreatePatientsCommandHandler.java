package com.kynsof.patients.application.command.patients.create;

import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.rules.dependent.DependentMustBeUniqueRule;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerCreateCustomerEventService;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerCreatePatientsEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.CustomerKafka;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePatientsCommandHandler implements ICommandHandler<CreatePatientsCommand> {

    private final IPatientsService serviceImpl;
    private final IContactInfoService contactInfoService;
    private final IGeographicLocationService geographicLocationService;


    private final ProducerCreatePatientsEventService patientEventService;
    private final ProducerCreateCustomerEventService createCustomerEventService;

    public CreatePatientsCommandHandler(IPatientsService serviceImpl, IContactInfoService contactInfoService,
                                        IGeographicLocationService geographicLocationService,
                                        ProducerCreatePatientsEventService patientEventService,
                                        ProducerCreateCustomerEventService createCustomerEventService
    ) {
        this.serviceImpl = serviceImpl;
        this.contactInfoService = contactInfoService;
        this.geographicLocationService = geographicLocationService;
        this.patientEventService = patientEventService;
        this.createCustomerEventService = createCustomerEventService;
    }

    @Override
    public void handle(CreatePatientsCommand command) {
        UUID idPatient = UUID.randomUUID();
        RulesChecker.checkRule(new DependentMustBeUniqueRule(this.serviceImpl, command.getIdentification(), idPatient));
        PatientDto patientDto = new PatientDto(
                idPatient,
                command.getIdentification(),
                command.getName(),
                command.getLastName(),
                command.getGender(),
                Status.ACTIVE,
                command.getWeight(),
                command.getHeight(),
                command.getHasDisability(),
                command.getIsPregnant(),
                command.getPhoto(),
                command.getDisabilityType(),
                command.getGestationTime());

        UUID id = serviceImpl.create(patientDto);
        command.setId(id);
        patientDto.setId(id);

        GeographicLocationDto geographicLocationDto = geographicLocationService.findById(
                command.getCreateContactInfoRequest().getGeographicLocationId());
        contactInfoService.create(new ContactInfoDto(
                UUID.randomUUID(),
                patientDto,
                command.getCreateContactInfoRequest().getEmail(),
                command.getCreateContactInfoRequest().getTelephone(),
                command.getCreateContactInfoRequest().getAddress(),
                command.getCreateContactInfoRequest().getBirthdayDate(),
                Status.ACTIVE,
                geographicLocationDto
        ));

        this.patientEventService.create(
                patientDto, 
                command.getCreateContactInfoRequest().getBirthdayDate()
        );

        this.createCustomerEventService.create(new CustomerKafka(
                patientDto.getId().toString(), 
                patientDto.getName(), 
                patientDto.getLastName(), 
                command.getCreateContactInfoRequest().getEmail()
        ));
    }
}
