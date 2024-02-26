package com.kynsof.treatments.application.command.externalConsultation.delete;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PatientsExternalConsultationCommand implements ICommand {

    private UUID id;

    @Override
    public ICommandMessage getMessage() {
        return new PatientExternalConsultationMessage(id);
    }

}
