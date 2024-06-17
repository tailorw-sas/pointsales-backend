package com.kynsoft.rrhh.application.command.assistant.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import org.springframework.stereotype.Component;

@Component
public class DeleteAssistantCommandHandler implements ICommandHandler<DeleteAssistantCommand> {

    private final IAssistantService service;

    public DeleteAssistantCommandHandler(IAssistantService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteAssistantCommand command) {
        AssistantDto delete = this.service.findById(command.getId());

        service.delete(delete);
    }

}
