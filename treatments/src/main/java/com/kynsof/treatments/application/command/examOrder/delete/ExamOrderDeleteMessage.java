package com.kynsof.treatments.application.command.examOrder.delete;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ExamOrderDeleteMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "DELETE_EXAM_ORDER";

    public ExamOrderDeleteMessage(UUID id) {
        this.id = id;
    }

}
