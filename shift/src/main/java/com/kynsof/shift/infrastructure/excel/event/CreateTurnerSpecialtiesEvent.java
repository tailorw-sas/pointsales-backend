package com.kynsof.shift.infrastructure.excel.event;

import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class CreateTurnerSpecialtiesEvent extends ApplicationEvent {

    private final List<CreateTurnerSpecialtiesCommand> command;
    public CreateTurnerSpecialtiesEvent(Object source, List<CreateTurnerSpecialtiesCommand> command) {
        super(source);
        this.command = command;
    }
}
