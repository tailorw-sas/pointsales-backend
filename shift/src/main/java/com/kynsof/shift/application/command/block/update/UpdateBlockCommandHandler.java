package com.kynsof.shift.application.command.block.update;

import com.kynsof.shift.domain.dto.BlockDto;
import com.kynsof.shift.domain.service.IBlockService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateBlockCommandHandler implements ICommandHandler<UpdateBlockCommand> {

    private final IBlockService service;

    public UpdateBlockCommandHandler(IBlockService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateBlockCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Service Type ID cannot be null."));

        BlockDto update = this.service.findById(command.getId());


        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());
        update.setCode(command.getCode());
        update.setStatus(command.getStatus());
        service.update(update);
    }
}
