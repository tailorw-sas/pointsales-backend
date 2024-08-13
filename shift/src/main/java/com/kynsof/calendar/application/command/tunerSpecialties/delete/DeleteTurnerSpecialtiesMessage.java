package com.kynsof.calendar.application.command.tunerSpecialties.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteTurnerSpecialtiesMessage implements ICommandMessage {

    private final UUID id;

    public DeleteTurnerSpecialtiesMessage(UUID id) {
        this.id = id;
    }

}
