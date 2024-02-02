package com.kynsof.patients.application.command.contactInfo.delete;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.service.IContactInfoService;
import org.springframework.stereotype.Component;

@Component
public class DeleteContactInfoCommandHandler implements ICommandHandler<DeleteContactInfoCommand> {

    private final IContactInfoService serviceImpl;

    public DeleteContactInfoCommandHandler(IContactInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteContactInfoCommand command) {

        serviceImpl.delete(command.getId());
    }

}
