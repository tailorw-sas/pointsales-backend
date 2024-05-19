package com.kynsof.treatments.application.command.exam.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ExamDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_EXAM";

    public ExamDeleteMessage(UUID id) {
        this.id = id;
    }

}
