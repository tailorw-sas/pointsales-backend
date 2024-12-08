package com.kynsof.treatments.application.command.optometryExam.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OptometryExamDeleteMessage implements ICommandMessage {

    private final UUID id;
    private final String command = "DELETE_OPTOMETRY_EXAM";

    public OptometryExamDeleteMessage(UUID id) {
        this.id = id;
    }
}