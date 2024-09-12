package com.kynsoft.rrhh.application.command.assistant.create;

import com.kynsof.share.core.domain.EUserType;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.kafka.entity.DoctorKafka;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import com.kynsoft.rrhh.domain.dto.BusinessDto;
import com.kynsoft.rrhh.domain.dto.CreateUserSystemRequest;
import com.kynsoft.rrhh.domain.dto.UserBusinessRelationDto;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import com.kynsoft.rrhh.domain.interfaces.services.IBusinessService;
import com.kynsoft.rrhh.domain.interfaces.services.IUserBusinessRelationService;
import com.kynsoft.rrhh.domain.rules.assistant.AssistantEmailMustBeUniqueRule;
import com.kynsoft.rrhh.domain.rules.assistant.AssistantIdentificationMustBeUniqueRule;
import com.kynsoft.rrhh.infrastructure.services.UserSystemService;
import com.kynsoft.rrhh.infrastructure.services.kafka.producer.assistant.ProducerReplicateAssistantService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.UUID;

// Otros imports

@Component
public class CreateAssistantCommandHandler implements ICommandHandler<CreateAssistantCommand> {

    private final IAssistantService service;
    private final IBusinessService businessService;
    private final IUserBusinessRelationService userBusinessRelationService;
    private final ProducerReplicateAssistantService producerReplicateAssistantService;
    private final UserSystemService userSystemService;



// Inyección de RestTemplate
    public CreateAssistantCommandHandler(IAssistantService service, IBusinessService businessService,
                                         IUserBusinessRelationService userBusinessRelationService,
                                         ProducerReplicateAssistantService producerReplicateAssistantService, UserSystemService userSystemService) {
        this.service = service;
        this.businessService = businessService;
        this.userBusinessRelationService = userBusinessRelationService;
        this.producerReplicateAssistantService = producerReplicateAssistantService;

        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(CreateAssistantCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getStatus(), "Assistant.status", "Assistant status cannot be null."));

        RulesChecker.checkRule(new AssistantEmailMustBeUniqueRule(this.service, command.getEmail()));
        RulesChecker.checkRule(new AssistantIdentificationMustBeUniqueRule(this.service, command.getIdentification()));
        BusinessDto businessDto = this.businessService.findById(command.getBusiness());

        try {
           var id = consumeCreateUserSystemService(command);
            AssistantDto assistantSave = new AssistantDto(
                    UUID.fromString(id),
                    command.getIdentification(),
                    command.getCode(),
                    command.getEmail(),
                    command.getName(),
                    command.getLastName(),
                    command.getStatus(),
                    command.getPhoneNumber(),
                    command.getImage(),
                    command.getDepartment()
            );

            service.create(assistantSave);

            this.userBusinessRelationService.create(new UserBusinessRelationDto(UUID.randomUUID(),
                    assistantSave, businessDto, "ACTIVE", LocalDateTime.now()));

            producerReplicateAssistantService.create(new DoctorKafka(
                assistantSave.getId(),
                assistantSave.getIdentification(),
                assistantSave.getCode(),
                assistantSave.getEmail(),
                assistantSave.getName(),
                assistantSave.getLastName(),
                assistantSave.getImage(),
                command.getBusiness().toString()
        ));
        } catch (InterruptedException | URISyntaxException | IOException ex) {
            throw new RuntimeException(ex);
        }




        // Consumir el servicio createUserSystem desde otro microservicio

    }

    // Método para consumir el servicio createUserSystem
    private String consumeCreateUserSystemService(CreateAssistantCommand command) throws IOException, URISyntaxException, InterruptedException {
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