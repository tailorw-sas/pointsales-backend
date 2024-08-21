package com.kynsof.shift.application.command.place.update;

import com.kynsof.shift.domain.dto.BlockDto;
import com.kynsof.shift.domain.dto.PlaceDto;
import com.kynsof.shift.domain.service.IBlockService;
import com.kynsof.shift.domain.service.IPlaceService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdatePlaceCommandHandler implements ICommandHandler<UpdatePlaceCommand> {

    private final IPlaceService service;
    private final IBlockService blockService;

    public UpdatePlaceCommandHandler(IPlaceService service, IBlockService blockService) {
        this.service = service;
        this.blockService = blockService;
    }

    @Override
    public void handle(UpdatePlaceCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "Service Type ID cannot be null."));

        PlaceDto update = this.service.findById(command.getId());
        BlockDto blockDto = this.blockService.findById(command.getBlock());

        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());
        update.setCode(command.getCode());
        update.setStatus(command.getStatus());
        update.setBlock(blockDto);
        service.update(update);
    }
}
