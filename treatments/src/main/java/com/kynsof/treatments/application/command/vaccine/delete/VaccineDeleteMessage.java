package com.kynsof.treatments.application.command.vaccine.delete;



import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class VaccineDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_VACCINE";

    public VaccineDeleteMessage(UUID id) {
        this.id = id;
    }

}
