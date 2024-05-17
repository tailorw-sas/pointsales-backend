package com.kynsof.treatments.application.command.treatment.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TreatmentDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_TREATMENT";

    public TreatmentDeleteMessage(UUID id) {
        this.id = id;
    }

}
