package com.kynsoft.rrhh.application.command.doctor.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateDoctorMessage implements ICommandMessage {
    private final UUID id;

    public CreateDoctorMessage(UUID id) {
        this.id = id;
    }
}