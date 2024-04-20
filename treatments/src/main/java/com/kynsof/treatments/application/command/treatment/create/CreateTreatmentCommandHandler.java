package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class CreateTreatmentCommandHandler implements ICommandHandler<CreateTreatmentCommand> {

    private final ITreatmentService serviceImpl;

    public CreateTreatmentCommandHandler(ITreatmentService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateTreatmentCommand command) {
        serviceImpl.create(new TreatmentDto(
                command.getId(), 
                command.getDescription(), 
                command.getMedication(), 
                command.getDose(), 
                command.getFrequency(), 
                command.getDuration()
        ));
    }
}
