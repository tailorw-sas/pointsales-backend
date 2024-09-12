package com.kynsoft.rrhh.application.command.doctor.create;

import com.kynsof.share.core.domain.EUserType;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.kafka.entity.DoctorKafka;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsoft.rrhh.domain.dto.BusinessDto;
import com.kynsoft.rrhh.domain.dto.CreateUserSystemRequest;
import com.kynsoft.rrhh.domain.dto.DoctorDto;
import com.kynsoft.rrhh.domain.dto.UserBusinessRelationDto;
import com.kynsoft.rrhh.domain.interfaces.services.IBusinessService;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;
import com.kynsoft.rrhh.domain.interfaces.services.IUserBusinessRelationService;
import com.kynsoft.rrhh.domain.rules.doctor.DoctorCodeMustBeUniqueRule;
import com.kynsoft.rrhh.domain.rules.doctor.DoctorEmailMustBeUniqueRule;
import com.kynsoft.rrhh.domain.rules.doctor.DoctorIdentificationMustBeUniqueRule;
import com.kynsoft.rrhh.domain.rules.users.UserSystemEmailValidateRule;
import com.kynsoft.rrhh.infrastructure.services.UserSystemService;
import com.kynsoft.rrhh.infrastructure.services.kafka.producer.doctor.ProducerReplicateDoctorService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateDoctorCommandHandler implements ICommandHandler<CreateDoctorCommand> {

    private final IDoctorService service;
    private final IBusinessService businessService;
    private final ProducerReplicateDoctorService producerReplicateDoctorService;
    private final IUserBusinessRelationService userBusinessRelationService;
    private final UserSystemService userSystemService;
    public CreateDoctorCommandHandler(IDoctorService service, IBusinessService businessService,
                                      ProducerReplicateDoctorService producerReplicateDoctorService, IUserBusinessRelationService userBusinessRelationService, UserSystemService userSystemService) {
        this.service = service;
        this.businessService = businessService;
        this.producerReplicateDoctorService = producerReplicateDoctorService;
        this.userBusinessRelationService = userBusinessRelationService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(CreateDoctorCommand command) {
        RulesChecker.checkRule(new UserSystemEmailValidateRule(command.getEmail()));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getStatus(), "Doctor.status", "Doctor status cannot be null."));
        RulesChecker.checkRule(new DoctorEmailMustBeUniqueRule(this.service, command.getEmail()));
        RulesChecker.checkRule(new DoctorIdentificationMustBeUniqueRule(this.service, command.getIdentification()));
        RulesChecker.checkRule(new DoctorCodeMustBeUniqueRule(this.service, command.getCode(), command.getId()));

        BusinessDto businessDto = this.businessService.findById(command.getBusiness());
        DoctorDto doctorSave = new DoctorDto(
                command.getId(),
                command.getIdentification(),
                command.getCode(),
                command.getEmail(),
                command.getName(),
                command.getLastName(),
                command.getStatus(),
                command.getRegisterNumber(),
                command.getLanguage(),
                command.isExpress(),
                command.getPhoneNumber(),
                command.getImage()
        );

        try {
            var id = consumeCreateUserSystemService(command);

            service.create(doctorSave);
            this.userBusinessRelationService.create(new UserBusinessRelationDto(UUID.randomUUID(),
                    doctorSave,businessDto, "ACTIVE", LocalDateTime.now()));

            producerReplicateDoctorService.create(new DoctorKafka(
                    UUID.fromString(id),
                    doctorSave.getIdentification(),
                    doctorSave.getCode(),
                    doctorSave.getEmail(),
                    doctorSave.getName(),
                    doctorSave.getLastName(),
                    doctorSave.getImage(),
                    command.getBusiness().toString()
            ));
        }catch (Exception exception){
            throw new BusinessException(DomainErrorMessage.DOCTOR_NOT_FOUND, "Ocurrió un error al crear al usuario.");
        }
    }

    // Método para consumir el servicio createUserSystem
    private String consumeCreateUserSystemService(CreateDoctorCommand command) throws IOException, URISyntaxException, InterruptedException {
        CreateUserSystemRequest createUserSystemRequest = new CreateUserSystemRequest();
        createUserSystemRequest.setUserName(command.getEmail());
        createUserSystemRequest.setEmail(command.getEmail());
        createUserSystemRequest.setName(command.getName());
        createUserSystemRequest.setLastName(command.getLastName());
        createUserSystemRequest.setPassword("defaultPassword"); // Ajusta según tus necesidades
        createUserSystemRequest.setUserType(EUserType.ASSISTANTS); // Ajusta si es necesario
        createUserSystemRequest.setImage(command.getImage());

        return userSystemService.createUserSystem(createUserSystemRequest);

    }
}