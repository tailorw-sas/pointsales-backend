package com.kynsof.calendar.application.command.businessService.delete;

import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteBusinessModuleCommandHandler implements ICommandHandler<DeleteBusinessModuleCommand> {

    private final IBusinessServicesService serviceImpl;

    public DeleteBusinessModuleCommandHandler(IBusinessServicesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteBusinessModuleCommand command) {

        BusinessServicesDto delete = this.serviceImpl.findById(command.getId());
        serviceImpl.delete(delete);
    }

}
