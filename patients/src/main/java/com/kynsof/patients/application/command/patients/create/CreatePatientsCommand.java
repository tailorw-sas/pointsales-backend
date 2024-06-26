package com.kynsof.patients.application.command.patients.create;

import com.kynsof.patients.application.command.patients.create.request.CreatePatientContactInfoRequest;
import com.kynsof.patients.application.command.patients.create.request.CreatePatientsRequest;
import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
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
    private GenderType gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private String photo;
    private int gestationTime;
    private CreatePatientContactInfoRequest createContactInfoRequest;
    private DisabilityType disabilityType;

    public CreatePatientsCommand(UUID id,String identification, String name, String lastName, GenderType gender, Double weight,
                                 Double height, Boolean hasDisability, Boolean isPregnant, String photo,
                                 int gestationTime, CreatePatientContactInfoRequest createContactInfoRequest,DisabilityType disabilityType){
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.hasDisability = hasDisability;
        this.isPregnant = isPregnant;
        this.photo = photo;
        this.gestationTime = gestationTime;
        this.createContactInfoRequest = createContactInfoRequest;
        this.disabilityType = disabilityType;

    }

    public static CreatePatientsCommand fromRequest(CreatePatientsRequest request) {
        return new CreatePatientsCommand(request.getId(), request.getIdentification(), request.getName(),
                request.getLastName(), request.getGender(), request.getWeight(),request.getHeight(),
                request.getHasDisability(),request.getIsPregnant(), request.getImage(),request.getGestationTime(),
                request.getContactInfo(), request.getDisabilityType());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreatePatientMessage(id);
    }
}
