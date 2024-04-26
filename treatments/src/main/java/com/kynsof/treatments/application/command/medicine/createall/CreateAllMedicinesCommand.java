package com.kynsof.treatments.application.command.medicine.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.application.command.medicine.create.CreateMedicineRequest;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllMedicinesCommand implements ICommand {

    private List<CreateMedicineRequest> payload;

    public CreateAllMedicinesCommand(PayloadMedicine payload) {
        this.payload = payload.getMedicines();
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllMedicineMessage();
    }
}
