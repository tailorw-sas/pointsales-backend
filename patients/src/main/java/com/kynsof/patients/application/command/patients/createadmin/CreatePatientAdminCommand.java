package com.kynsof.patients.application.command.patients.createadmin;

import com.kynsof.patients.domain.dto.enumTye.DisabilityType;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePatientAdminCommand implements ICommand {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private GenderType gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private byte[] photo;
    private int gestationTime;
    private DisabilityType disabilityType;

    public CreatePatientAdminCommand(String identification, String name, String lastName, GenderType gender, Double weight,
                                 Double height, Boolean hasDisability, Boolean isPregnant, byte[] photo,
                                 int gestationTime, DisabilityType disabilityType){

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
        this.disabilityType = disabilityType;

    }

    public static CreatePatientAdminCommand fromRequest(CreatePatientsAdminRequest request) {
        return new CreatePatientAdminCommand(request.getIdentification(), request.getName(), request.getLastName(), request.getGender(),
                request.getWeight(),request.getHeight(),request.getHasDisability(),request.getIsPregnant(),
                request.getPhoto(),request.getGestationTime(), request.getDisabilityType());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreatePatientAdminMessage(id);
    }
}