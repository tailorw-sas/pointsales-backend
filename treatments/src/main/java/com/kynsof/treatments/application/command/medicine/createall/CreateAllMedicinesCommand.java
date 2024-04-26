package com.kynsof.treatments.application.command.medicine.createall;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllMedicinesCommand implements ICommand {

    private List<String> payload;

    public CreateAllMedicinesCommand(List<String> payload) {
        this.payload = List.copyOf(payload);
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateAllMedicineMessage();
    }
}
