package com.kynsof.treatments.application.command.medicine.update;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.service.IMedicinesService;
import org.springframework.stereotype.Component;

@Component
public class UpdateMedicineCommandHandler implements ICommandHandler<UpdateMedicineCommand> {

    private final IMedicinesService serviceImpl;

    public UpdateMedicineCommandHandler(IMedicinesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(UpdateMedicineCommand command) {
        MedicinesDto update = this.serviceImpl.findById(command.getId());

        UpdateIfNotNull.updateIfStringNotNull(update::setName, command.getName());

        this.serviceImpl.update(update);
    }
}
