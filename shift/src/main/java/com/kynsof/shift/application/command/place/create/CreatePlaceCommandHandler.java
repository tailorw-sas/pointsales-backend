package com.kynsof.shift.application.command.place.create;

import com.kynsof.shift.domain.dto.BlockDto;
import com.kynsof.shift.domain.dto.BusinessDto;
import com.kynsof.shift.domain.dto.PlaceDto;
import com.kynsof.shift.domain.service.IBlockService;
import com.kynsof.shift.domain.service.IBusinessService;
import com.kynsof.shift.domain.service.IPlaceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreatePlaceCommandHandler implements ICommandHandler<CreatePlaceCommand> {

    private final IPlaceService service;
    private final IBlockService blockService;
    private final IBusinessService businessService;

    public CreatePlaceCommandHandler(IPlaceService service, IBlockService blockService, IBusinessService businessService) {
        this.service = service;
        this.blockService = blockService;
        this.businessService = businessService;
    }

    @Override
    public void handle(CreatePlaceCommand command) {
        BlockDto blockDto = this.blockService.findById(command.getBlock());
        BusinessDto businessDto = this.businessService.findById(command.getBusiness());
        UUID id = service.create(new PlaceDto(
                command.getId(),
                command.getName(),
                command.getStatus(),
                command.getCode(),
                blockDto,
                businessDto
        ));
        command.setId(id);
    }
}
