package com.kynsof.patients.application.command.patients.create;

import com.kynsof.patients.application.command.patients.create.request.CreatePatientContactInfoRequest;
import com.kynsof.patients.application.command.patients.create.request.CreatePatientsRequest;
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
    private String photo;
    private CreatePatientContactInfoRequest createContactInfoRequest;


    public CreatePatientsCommand(UUID id, String identification, String name, String lastName, GenderType gender,
                                 String photo, CreatePatientContactInfoRequest createContactInfoRequest) {
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.photo = photo;
        this.createContactInfoRequest = createContactInfoRequest;
    }

    public static CreatePatientsCommand fromRequest(CreatePatientsRequest request) {
        return new CreatePatientsCommand(request.getId(), request.getIdentification(), request.getName(),
                request.getLastName(), request.getGender(),
                request.getImage(),
                request.getContactInfo());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreatePatientMessage(id);
    }
}
