package com.kynsof.treatments.application.command.vaccine.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateVaccineCommand implements ICommand {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;


    public UpdateVaccineCommand(UUID id, String identification, String name, String lastName, String gender,
                                LocalDate birthDate){
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public static UpdateVaccineCommand fromRequest(UUID id, UpdateVaccineRequest request) {
        return new UpdateVaccineCommand(id,request.getIdentification(), request.getName(), request.getLastName(),
                request.getGender(),request.getBirthDate());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateVaccineMessage();
    }
}
