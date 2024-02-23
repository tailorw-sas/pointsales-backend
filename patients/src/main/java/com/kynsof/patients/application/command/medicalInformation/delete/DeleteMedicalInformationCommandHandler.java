package com.kynsof.patients.application.command.medicalInformation.delete;

import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteMedicalInformationCommandHandler implements ICommandHandler<DeleteMedicalInformationCommand> {

    private final IAdditionalInfoService serviceImpl;

    public DeleteMedicalInformationCommandHandler(IAdditionalInfoService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(DeleteMedicalInformationCommand command) {

        serviceImpl.delete(command.getId());
    }

}
