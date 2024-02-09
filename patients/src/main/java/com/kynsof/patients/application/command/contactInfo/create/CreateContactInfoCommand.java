package com.kynsof.patients.application.command.contactInfo.create;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreateContactInfoCommand implements ICommand {
    private UUID id;
    private UUID patientId;
    private String email;
    private String telephone;
    private String address;
    private LocalDate birthdayDate;
    private UUID geographicLocationId;


    public CreateContactInfoCommand(UUID patientId, String email, String telephone, String address, LocalDate localDate,
                                   UUID geographicLocationId ) {
        this.patientId = patientId;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.birthdayDate = localDate;
        this.geographicLocationId = geographicLocationId;
    }

    public static CreateContactInfoCommand fromRequest(CreateContactInfoRequest request) {
        return new CreateContactInfoCommand(request.getPatientId(), request.getEmail(), request.getTelephone(),
                request.getAddress(),request.getBirthdayDate(), request.getGeographicLocationId());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateContactInfoMessage(id);
    }
}
