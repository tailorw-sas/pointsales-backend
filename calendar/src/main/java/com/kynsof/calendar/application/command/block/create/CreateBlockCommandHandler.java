package com.kynsof.calendar.application.command.block.create;

import com.kynsof.calendar.domain.dto.BlockDto;
import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.service.IBlockService;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateBlockCommandHandler implements ICommandHandler<CreateBlockCommand> {


    private final IBlockService service;
    private final IBusinessService businessService;

    public CreateBlockCommandHandler(IBlockService service, IBusinessService businessService) {
        this.service = service;
        this.businessService = businessService;
    }

    @Override
    public void handle(CreateBlockCommand command) {
        BusinessDto businessDto = this.businessService.findById(command.getBusiness());
        UUID id = service.create(new BlockDto(
                command.getId(),
                command.getName(),
                command.getStatus(),
                command.getCode(),
                businessDto
        ));
        command.setId(id);
    }
}
