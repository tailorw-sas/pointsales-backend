package com.kynsof.treatments.application.command.vaccine.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.service.IVaccineService;
import org.springframework.stereotype.Component;

@Component
public class DeleteVaccineCommandHandler implements ICommandHandler<VaccineDeleteCommand> {

    private final IVaccineService serviceImpl;

    public DeleteVaccineCommandHandler(IVaccineService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(VaccineDeleteCommand command) {
        VaccineDto delete = this.serviceImpl.findById(command.getId());

        serviceImpl.delete(delete);
    }

}
