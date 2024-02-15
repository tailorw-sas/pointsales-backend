package com.kynsof.patients.application.command.patients.delete;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeletePatientsCommand implements ICommand {

    private UUID id;

    @Override
    public ICommandMessage getMessage() {
        return new PatientDeleteMessage(id);
    }

}
