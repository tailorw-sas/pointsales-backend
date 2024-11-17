package com.kynsof.treatments.application.command.businessProcedure.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.BusinessProcedureDto;
import com.kynsof.treatments.domain.service.IBusinessProcedureService;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessProcedureCommandHandler implements ICommandHandler<UpdateBusinessProcedureCommand> {

    private final IBusinessProcedureService businessProcedureService;


    public UpdateBusinessProcedureCommandHandler(IBusinessProcedureService businessProcedureService) {

        this.businessProcedureService = businessProcedureService;
    }

    @Override
    public void handle(UpdateBusinessProcedureCommand command) {

        BusinessProcedureDto businessProcedureDto = businessProcedureService.findById(command.getBusinessProcedureId());
        businessProcedureDto.setCode(command.getCode());
        businessProcedureDto.setPrice(command.getPrice());
        businessProcedureService.update(businessProcedureDto);
    }
}
