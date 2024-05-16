package com.kynsof.treatments.application.command.medicine.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.service.IMedicinesService;
import org.springframework.stereotype.Component;

@Component
public class DeleteMedicineCommandHandler implements ICommandHandler<MedicineDeleteCommand> {

    private final IMedicinesService serviceImpl;

    public DeleteMedicineCommandHandler(IMedicinesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(MedicineDeleteCommand command) {
        MedicinesDto delete = this.serviceImpl.findById(command.getId());

        serviceImpl.delete(delete);
    }

}
