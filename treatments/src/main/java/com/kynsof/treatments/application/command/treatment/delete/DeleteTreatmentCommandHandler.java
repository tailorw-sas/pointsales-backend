package com.kynsof.treatments.application.command.treatment.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.service.ITreatmentService;
import org.springframework.stereotype.Component;

@Component
public class DeleteTreatmentCommandHandler implements ICommandHandler<TreatmentDeleteCommand> {

    private final ITreatmentService serviceImpl;

    public DeleteTreatmentCommandHandler(ITreatmentService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(TreatmentDeleteCommand command) {
        TreatmentDto delete = this.serviceImpl.findById(command.getId());

        serviceImpl.delete(delete);
    }

}
