package com.kynsof.rrhh.application.command.doctor.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteDoctorMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_DOCTOR";

    public DeleteDoctorMessage(UUID id) {
        this.id = id;
    }

}
