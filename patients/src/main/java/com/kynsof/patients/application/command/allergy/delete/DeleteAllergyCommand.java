package com.kynsof.patients.application.command.allergy.delete;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeleteAllergyCommand implements ICommand {

    private UUID id;

    @Override
    public ICommandMessage getMessage() {
        return new DeleteAllergyMessage(id);
    }

}