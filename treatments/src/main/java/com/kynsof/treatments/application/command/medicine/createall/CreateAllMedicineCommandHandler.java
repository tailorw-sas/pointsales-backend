package com.kynsof.treatments.application.command.medicine.createall;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineRequest;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.service.IMedicinesService;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateAllMedicineCommandHandler implements ICommandHandler<CreateAllMedicinesCommand> {

    private final IMedicinesService serviceImpl;

    public CreateAllMedicineCommandHandler(IMedicinesService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void handle(CreateAllMedicinesCommand command) {
        for (CreateMedicineRequest createMedicineRequest : command.getPayload()) {
            MedicinesDto create = new MedicinesDto(
                    UUID.randomUUID(),
                    createMedicineRequest.getName()
            );
            this.serviceImpl.create(create);
        }

    }
}
