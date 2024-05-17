package com.kynsof.treatments.application.command.diagnosis.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DiagnosisDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_DIAGNOSIS";

    public DiagnosisDeleteMessage(UUID id) {
        this.id = id;
    }

}
