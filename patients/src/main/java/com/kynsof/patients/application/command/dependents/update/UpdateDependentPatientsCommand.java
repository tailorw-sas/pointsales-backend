package com.kynsof.patients.application.command.dependents.update;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateDependentPatientsCommand implements ICommand {
    private UUID id;
    private UUID primeId;
    private String identification;

    private String name;

    private String lastName;

    private String gender;


    public UpdateDependentPatientsCommand(UUID id,UUID primeId, String identification, String name, String lastName, String gender){
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.primeId = primeId;
    }

    public static UpdateDependentPatientsCommand fromRequest(UUID id, UpdateDependentPatientsRequest request) {
        return new UpdateDependentPatientsCommand(id,request.getPrimeId(),request.getIdentification(), request.getName(), request.getLastName(), request.getGender());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateDependentPatientMessage();
    }
}
