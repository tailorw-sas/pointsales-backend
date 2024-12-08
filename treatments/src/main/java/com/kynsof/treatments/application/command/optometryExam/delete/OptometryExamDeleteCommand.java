package com.kynsof.treatments.application.command.optometryExam.delete;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OptometryExamDeleteCommand implements ICommand {
    private final UUID id;

    public OptometryExamDeleteCommand(UUID id) {
        this.id = id;
    }

    @Override
    public ICommandMessage getMessage() {
        return new OptometryExamDeleteMessage(id);
    }
}