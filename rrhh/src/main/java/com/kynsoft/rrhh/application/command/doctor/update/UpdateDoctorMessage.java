package com.kynsoft.rrhh.application.command.doctor.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateDoctorMessage implements ICommandMessage {
    private final UUID id;

    public UpdateDoctorMessage(UUID id) {
        this.id = id;
    }
}