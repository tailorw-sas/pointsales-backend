package com.kynsof.treatments.application.command.medicine.createall;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateAllMedicineMessage implements ICommandMessage {

    private final String command = "CREATE_ALL_MEDICINE";

    public CreateAllMedicineMessage() {
    }

}
