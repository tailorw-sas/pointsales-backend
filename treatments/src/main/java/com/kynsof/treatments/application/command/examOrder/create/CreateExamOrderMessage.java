package com.kynsof.treatments.application.command.examOrder.create;

import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateExamOrderMessage implements ICommandMessage {

    private final UUID id;

    private final String command = "CREATE_EXAM_ORDER";

    public CreateExamOrderMessage(UUID id) {
        this.id = id;
    }

}
