package com.kynsof.patients.application.command.delete;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
