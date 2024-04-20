package com.kynsof.treatments.application.command.exam.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateExamMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_EXAM";

    public CreateExamMessage(UUID id) {
        this.id = id;
    }

}
