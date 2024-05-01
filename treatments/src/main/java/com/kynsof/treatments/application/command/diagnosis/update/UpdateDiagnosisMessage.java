package com.kynsof.treatments.application.command.diagnosis.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateDiagnosisMessage implements ICommandMessage {

    private final boolean result;

    private final String command = "UPDATE_DIAGNOSIS";

    public UpdateDiagnosisMessage(boolean id) {
        this.result = id;
    }

}
