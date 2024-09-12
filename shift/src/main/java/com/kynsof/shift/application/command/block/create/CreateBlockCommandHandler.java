package com.kynsof.shift.application.command.block.create;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.shift.domain.dto.BlockDto;
import com.kynsof.shift.domain.dto.BusinessDto;
import com.kynsof.shift.domain.service.IBlockService;
import com.kynsof.shift.domain.service.IBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.shift.domain.rules.service.block.BlockCodeMustBeUniqueRule;
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
        RulesChecker.checkRule(new BlockCodeMustBeUniqueRule(this.service, command.getCode(), command.getId()));
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
