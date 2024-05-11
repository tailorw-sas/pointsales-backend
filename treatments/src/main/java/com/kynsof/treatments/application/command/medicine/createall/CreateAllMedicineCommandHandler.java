package com.kynsof.treatments.application.command.medicine.createall;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.service.IMedicinesService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateAllMedicineCommandHandler implements ICommandHandler<CreateAllMedicinesCommand> {

    private final IMedicinesService serviceImpl;

    public CreateAllMedicineCommandHandler(IMedicinesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateAllMedicinesCommand command) {
        for (String name : command.getPayload()) {
            MedicinesDto create = new MedicinesDto(
                    UUID.randomUUID(),
                    name
            );
            try {
                this.serviceImpl.create(create);
            } catch (Exception e) {
            }
        }

    }
}
