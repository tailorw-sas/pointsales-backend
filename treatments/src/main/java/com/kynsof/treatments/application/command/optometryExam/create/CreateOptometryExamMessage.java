package com.kynsof.treatments.application.command.optometryExam.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateOptometryExamMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_OPTOMETRY_EXAM";

    public CreateOptometryExamMessage(UUID id) {
        this.id = id;
    }
}