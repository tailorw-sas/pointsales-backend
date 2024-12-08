package com.kynsof.treatments.application.command.optometryExam.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateOptometryExamMessage implements ICommandMessage {

    private final UUID id;
    private final String command = "UPDATE_OPTOMETRY_EXAM";

    public UpdateOptometryExamMessage(UUID id) {
        this.id = id;
    }
}