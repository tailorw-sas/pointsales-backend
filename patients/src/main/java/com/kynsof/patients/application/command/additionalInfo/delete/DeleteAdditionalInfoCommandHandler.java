package com.kynsof.patients.application.command.additionalInfo.delete;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import org.springframework.stereotype.Component;

@Component
public class DeleteAdditionalInfoCommandHandler implements ICommandHandler<DeleteAdditionalInfoCommand> {

    private final IAdditionalInfoService serviceImpl;

    public DeleteAdditionalInfoCommandHandler(IAdditionalInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteAdditionalInfoCommand command) {

        serviceImpl.delete(command.getId());
    }

}