package com.kynsof.patients.application.command.dependents.update;

import com.kynsof.patients.application.command.contactInfo.create.CreateContactInfoRequest;
import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.FamilyRelationship;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateDependentPatientsCommand implements ICommand {
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
    private LocalDate birthDate;
    private FamilyRelationship familyRelationship;
    private byte[] photo;
    private int gestationTime;
    private CreateContactInfoRequest createContactInfoRequest;
    private DisabilityType disabilityType;

    public UpdateDependentPatientsCommand(UUID id,UUID primeId, String identification, String name, String lastName,
                                          GenderType gender, Double weight, Double height, Boolean hasDisability,
                                          Boolean isPregnant, LocalDate birthDate, FamilyRelationship familyRelationship,
                                          byte[] photo, int gestationTime,
                                          CreateContactInfoRequest createContactInfoRequest, DisabilityType disabilityType){
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.primeId = primeId;
        this.weight = weight;
        this.height = height;
        this.hasDisability = hasDisability;
        this.isPregnant = isPregnant;
        this.birthDate = birthDate;
        this.familyRelationship = familyRelationship;
        this.photo = photo;
        this.gestationTime = gestationTime;
        this.createContactInfoRequest = createContactInfoRequest;
        this.disabilityType = disabilityType;

    }

    public static UpdateDependentPatientsCommand fromRequest(UUID id, UpdateDependentPatientsRequest request) {
        return new UpdateDependentPatientsCommand(id,request.getPrimeId(),request.getIdentification(),
                request.getName(), request.getLastName(), request.getGender(),
                request.getWeight(),request.getHeight(),request.getHasDisability(),request.getIsPregnant(),
                request.getBirthDate(), request.getFamilyRelationship(), request.getPhoto(),request.getGestationTime(),
                request.getCreateContactInfoRequest(), request.getDisabilityType());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateDependentPatientMessage();
    }
}
