package com.kynsof.treatments.application.command.patients.delete;

import com.kynsof.treatments.domain.bus.command.ICommand;
import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PatientsDeleteCommand implements ICommand {

    private UUID id;

    @Override
    public ICommandMessage getMessage() {
        return new PatientDeleteMessage(id);
    }

}
