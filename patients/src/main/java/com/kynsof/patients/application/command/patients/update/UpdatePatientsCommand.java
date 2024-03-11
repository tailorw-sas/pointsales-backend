package com.kynsof.patients.application.command.patients.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
public class UpdatePatientsCommand implements ICommand {
    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private String gender;
    private Double weight;
    private Double height;
    private Boolean hasDisability;
    private Boolean isPregnant;
    private MultipartFile photo;

    public UpdatePatientsCommand(UUID id, String identification, String name, String lastName, String gender, Double weight,
                                 Double height, Boolean hasDisability, Boolean isPregnant,
                                 MultipartFile photo){

        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.hasDisability = hasDisability;
        this.isPregnant = isPregnant;
        this.id = id;
        this.photo = photo;
    }

    public static UpdatePatientsCommand fromRequest(UUID id, UpdatePatientsRequest request) {
        return new UpdatePatientsCommand(id,request.getIdentification(), request.getName(), request.getLastName(), request.getGender(),
                request.getWeight(),request.getHeight(),request.getHasDisability(),request.getIsPregnant(),
                 request.getPhoto());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdatePatientMessage();
    }
}
