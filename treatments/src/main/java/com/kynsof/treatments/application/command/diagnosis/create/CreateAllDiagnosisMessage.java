package com.kynsof.treatments.application.command.diagnosis.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class CreateAllDiagnosisMessage implements ICommandMessage {

    private final String command = "CREATE_ALL_DIAGNOSIS";

    public CreateAllDiagnosisMessage() {
    }

}
