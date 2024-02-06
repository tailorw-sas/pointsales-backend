package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.treatments.domain.bus.command.ICommand;
import com.kynsof.treatments.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateExternalConsultationCommand implements ICommand {
    private UUID id;
    private String identification;

    private String name;

    private String lastName;

    private String gender;


    public UpdateExternalConsultationCommand(UUID id, String identification, String name, String lastName, String gender){
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
    }

    public static UpdateExternalConsultationCommand fromRequest(UUID id, UpdateExternalConsultationRequest request) {
        return new UpdateExternalConsultationCommand(id,request.getIdentification(), request.getName(), request.getLastName(), request.getGender());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateExternalConsultationMessage();
    }
}
