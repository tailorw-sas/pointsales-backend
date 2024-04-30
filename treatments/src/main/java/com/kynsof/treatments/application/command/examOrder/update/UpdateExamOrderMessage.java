package com.kynsof.treatments.application.command.examOrder.update;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateExamOrderMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "UPDATE_EXAM_ORDER";

    public UpdateExamOrderMessage(UUID id) {
        this.id = id;
    }

}
