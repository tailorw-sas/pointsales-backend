package com.kynsof.treatments.application.command.medicine.create;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import com.kynsof.treatments.domain.rules.medicines.MedicinesNameMustBeUniqueRule;
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
        RulesChecker.checkRule(new MedicinesNameMustBeUniqueRule(this.serviceImpl, command.getName(), command.getId()));
        MedicinesDto create = new MedicinesDto(
                command.getId(), 
                command.getName(),
                command.getPresentation()
        );
        this.serviceImpl.create(create);
    }
}
