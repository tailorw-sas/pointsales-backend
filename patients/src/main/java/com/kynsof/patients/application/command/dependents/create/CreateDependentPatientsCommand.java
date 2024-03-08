package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private LocalDate birthDate;
    private FamilyRelationship familyRelationship;

    public CreateDependentPatientsCommand(UUID primeId, String identification, String name, String lastName, String gender, Double weight,
                                          Double height, Boolean hasDisability, Boolean isPregnant, LocalDate birthDate, FamilyRelationship familyRelationship){
        this.primeId = primeId;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.hasDisability = hasDisability;
        this.isPregnant = isPregnant;
        this.birthDate = birthDate;
        this.familyRelationship = familyRelationship;
    }

    public static CreateDependentPatientsCommand fromRequest(CreateDependentPatientsRequest request) {
        return new CreateDependentPatientsCommand(request.getPrimeId(),request.getIdentification(), request.getName(),
                request.getLastName(), request.getGender(),request.getWeight(),request.getHeight(),
                request.getHasDisability(),request.getIsPregnant(), request.getBirthDate(), request.getFamilyRelationship());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateDependentPatientMessage(id);
    }
}
