package com.kynsof.treatments.application.command.exam.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateExamMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_EXAM";

    public UpdateExamMessage(UUID id) {
        this.id = id;
    }

}
