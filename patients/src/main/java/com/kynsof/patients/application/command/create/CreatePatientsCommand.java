package com.kynsof.patients.application.command.create;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

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

    public static CreatePatientsCommand fromRequest(PatientsRequest request) {
        return new CreatePatientsCommand(request.getIdentification(), request.getName(), request.getLastName(), request.getGender());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreatePatientMessage(this.id);
    }
}
