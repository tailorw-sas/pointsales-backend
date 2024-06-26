package com.kynsof.calendar.application.command.place.create;

import com.kynsof.calendar.domain.dto.BlockDto;
import com.kynsof.calendar.domain.dto.PlaceDto;
import com.kynsof.calendar.domain.service.IBlockService;
import com.kynsof.calendar.domain.service.IPlaceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePlaceCommandHandler implements ICommandHandler<CreatePlaceCommand> {

    private final IPlaceService service;
    private final IBlockService blockService;

    public CreatePlaceCommandHandler(IPlaceService service, IBlockService blockService) {
        this.service = service;
        this.blockService = blockService;
    }

    @Override
    public void handle(CreatePlaceCommand command) {
        BlockDto blockDto = this.blockService.findById(command.getBlock());
        UUID id = service.create(new PlaceDto(
                command.getId(),
                command.getName(),
                command.getStatus(),
                command.getCode(),
                blockDto
        ));
        command.setId(id);
    }
}
