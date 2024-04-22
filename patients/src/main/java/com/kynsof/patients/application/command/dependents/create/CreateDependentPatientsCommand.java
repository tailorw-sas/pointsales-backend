package com.kynsof.patients.application.command.dependents.create;

import com.kynsof.patients.application.command.dependents.create.request.CreateDependentContactInfoRequest;
import com.kynsof.patients.application.command.dependents.create.request.CreateDependentPatientsRequest;
import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
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
    private GenderType gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private FamilyRelationship familyRelationship;
    private byte[] photo;
    private int gestationTime;
    private CreateDependentContactInfoRequest createContactInfoRequest;
    private DisabilityType disabilityType;

    public CreateDependentPatientsCommand(UUID primeId, String identification, String name, String lastName, GenderType gender, Double weight,
                                          Double height, Boolean hasDisability, Boolean isPregnant,
                                          FamilyRelationship familyRelationship, byte[] photo, int gestationTime,
                                          CreateDependentContactInfoRequest createContactInfoRequest, DisabilityType disabilityType) {
        this.primeId = primeId;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.hasDisability = hasDisability;
        this.isPregnant = isPregnant;
        this.familyRelationship = familyRelationship;
        this.photo = photo;
        this.gestationTime = gestationTime;
        this.createContactInfoRequest = createContactInfoRequest;
        this.disabilityType = disabilityType;
    }

    public static CreateDependentPatientsCommand fromRequest(CreateDependentPatientsRequest request) {
        return new CreateDependentPatientsCommand(request.getPrimeId(), request.getIdentification(), request.getName(),
                request.getLastName(), request.getGender(), request.getWeight(), request.getHeight(),
                request.getHasDisability(), request.getIsPregnant(),  request.getFamilyRelationship(),
                request.getImage(), request.getGestationTime(), request.getContactInfo(), request.getDisabilityType());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateDependentPatientMessage(id);
    }
}
