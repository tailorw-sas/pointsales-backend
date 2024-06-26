package com.kynsof.calendar.application.command.block.create;

import com.kynsof.calendar.domain.dto.BlockDto;
import com.kynsof.calendar.domain.service.IBlockService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBlockCommandHandler implements ICommandHandler<CreateBlockCommand> {

    private final IBlockService service;

    public CreateBlockCommandHandler(IBlockService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateBlockCommand command) {
        UUID id = service.create(new BlockDto(
                command.getId(),
                command.getName(),
                command.getStatus(),
                command.getCode()
        ));
        command.setId(id);
    }
}
