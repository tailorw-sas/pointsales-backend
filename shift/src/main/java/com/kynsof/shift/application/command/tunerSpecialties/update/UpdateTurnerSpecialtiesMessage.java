package com.kynsof.shift.application.command.tunerSpecialties.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class UpdateTurnerSpecialtiesMessage implements ICommandMessage {

    private final String id;

    public UpdateTurnerSpecialtiesMessage(String id) {
        this.id = id;
    }

}
