package com.kynsoft.rrhh.application.command.assistant.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import org.springframework.stereotype.Component;

@Component
public class UpdateAssistantCommandHandler implements ICommandHandler<UpdateAssistantCommand> {

    private final IAssistantService service;


    public UpdateAssistantCommandHandler(IAssistantService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateAssistantCommand command) {

        AssistantDto assistantDto = service.findById(command.getId());
        assistantDto.setName(command.getName());
        assistantDto.setEmail(command.getEmail());
        assistantDto.setDepartment(command.getDepartment());
        assistantDto.setIdentification(command.getIdentification());
        assistantDto.setLastName(command.getLastName());
        assistantDto.setStatus(command.getStatus());
        assistantDto.setPhoneNumber(command.getPhoneNumber());
        assistantDto.setImage(command.getImage());

        service.update(assistantDto);
    }
}
