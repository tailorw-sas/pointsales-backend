package com.kynsof.patients.application.command.patients.create;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import com.kynsof.share.core.domain.bus.command.ICommand;
import java.util.UUID;

@Getter
@Setter
public class CreatePatientsCommand implements ICommand {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;


    public CreatePatientsCommand(String identification, String name, String lastName, String gender){

        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
    }

    public static CreatePatientsCommand fromRequest(CreatePatientsRequest request) {
        return new CreatePatientsCommand(request.getIdentification(), request.getName(), request.getLastName(), request.getGender());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreatePatientMessage(id);
    }
}
