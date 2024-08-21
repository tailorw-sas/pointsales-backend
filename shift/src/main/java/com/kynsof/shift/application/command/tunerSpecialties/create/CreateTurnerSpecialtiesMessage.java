package com.kynsof.shift.application.command.tunerSpecialties.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateTurnerSpecialtiesMessage implements ICommandMessage {

    private final UUID id;

    public CreateTurnerSpecialtiesMessage(UUID id) {
        this.id = id;
    }

}
