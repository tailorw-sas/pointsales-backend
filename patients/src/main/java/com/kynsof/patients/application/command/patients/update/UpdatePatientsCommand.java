package com.kynsof.patients.application.command.patients.update;

import com.kynsof.patients.application.command.patients.create.request.CreatePatientContactInfoRequest;
import com.kynsof.patients.domain.dto.enumTye.GenderType;
import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePatientsCommand implements ICommand {

    private UUID id;
    private String identification;
    private String name;
    private String lastName;
    private GenderType gender;
    private String photo;
    private CreatePatientContactInfoRequest createContactInfoRequest;


    public UpdatePatientsCommand(UUID id, String identification, String name, String lastName, GenderType gender,
            String photo,
            CreatePatientContactInfoRequest createContactInfoRequest) {

        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.id = id;
        this.photo = photo;
        this.createContactInfoRequest = createContactInfoRequest;

    }

    public static UpdatePatientsCommand fromRequest(UUID id, UpdatePatientsRequest request) {
        return new UpdatePatientsCommand(id, request.getIdentification(), request.getName(), request.getLastName(), request.getGender(),
                request.getImage(),  request.getContactInfo());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdatePatientMessage();
    }
}
