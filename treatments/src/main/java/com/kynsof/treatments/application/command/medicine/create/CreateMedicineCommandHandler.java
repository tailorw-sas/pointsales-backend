package com.kynsof.treatments.application.command.medicine.create;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.service.IMedicinesService;
import org.springframework.stereotype.Component;

@Component
public class CreateMedicineCommandHandler implements ICommandHandler<CreateMedicineCommand> {

    private final IMedicinesService serviceImpl;

    public CreateMedicineCommandHandler(IMedicinesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateMedicineCommand command) {
        MedicinesDto create = new MedicinesDto(
                command.getId(), 
                command.getName()
        );
        this.serviceImpl.create(create);
    }
}
