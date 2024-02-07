package com.kynsof.patients.application.command.allergy.delete;

import com.kynsof.patients.domain.bus.command.ICommandHandler;
import com.kynsof.patients.domain.service.IContactInfoService;
import org.springframework.stereotype.Component;

@Component
public class DeleteAllergyCommandHandler implements ICommandHandler<DeleteAllergyCommand> {

    private final IContactInfoService serviceImpl;

    public DeleteAllergyCommandHandler(IContactInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteAllergyCommand command) {

        serviceImpl.delete(command.getId());
    }

}