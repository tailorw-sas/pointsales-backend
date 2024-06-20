package com.kynsoft.rrhh.application.command.assistant.create;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import com.kynsoft.rrhh.domain.rules.assistant.AssistantEmailMustBeUniqueRule;
import com.kynsoft.rrhh.domain.rules.assistant.AssistantIdentificationMustBeUniqueRule;
import org.springframework.stereotype.Component;

@Component
public class CreateAssistantCommandHandler implements ICommandHandler<CreateAssistantCommand> {

    private final IAssistantService service;

    public CreateAssistantCommandHandler(IAssistantService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateAssistantCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getStatus(), "Assistant.status", "Assistant status cannot be null."));

        RulesChecker.checkRule(new AssistantEmailMustBeUniqueRule(this.service, command.getEmail()));
        RulesChecker.checkRule(new AssistantIdentificationMustBeUniqueRule(this.service, command.getIdentification()));

        AssistantDto assistantSave = new AssistantDto(
                command.getId(),
                command.getIdentification(),
                command.getEmail(),
                command.getName(),
                command.getLastName(),
                command.getStatus(),
                command.getPhoneNumber(),
                command.getImage(),
                command.getDepartment()
        );

        service.create(assistantSave);
    }
}