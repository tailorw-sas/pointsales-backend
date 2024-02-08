package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateDependentPatientsCommand implements ICommand {
    private UUID id;
    private UUID primeId;
    private String identification;
    private String name;
    private String lastName;
    private String gender;

    public CreateDependentPatientsCommand(UUID primeId, String identification, String name, String lastName, String gender){
        this.primeId = primeId;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
    }

    public static CreateDependentPatientsCommand fromRequest(CreateDependentPatientsRequest request) {
        return new CreateDependentPatientsCommand(request.getPrimeId(),request.getIdentification(), request.getName(),
                request.getLastName(), request.getGender());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateDependentPatientMessage(id);
    }
}
