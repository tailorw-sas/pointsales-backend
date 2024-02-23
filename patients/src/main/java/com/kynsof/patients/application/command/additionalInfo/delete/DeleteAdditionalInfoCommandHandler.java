package com.kynsof.patients.application.command.additionalInfo.delete;

import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
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
