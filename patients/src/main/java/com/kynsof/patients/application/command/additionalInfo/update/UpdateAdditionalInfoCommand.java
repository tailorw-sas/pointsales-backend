package com.kynsof.patients.application.command.additionalInfo.update;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class UpdateAdditionalInfoCommand implements ICommand {
    private UUID id;
    private UUID patientId;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;


    public UpdateAdditionalInfoCommand(UUID id, UUID patientId, String maritalStatus, String occupation,
                                       String emergencyContactName, String emergencyContactPhone) {
        this.id = id;
        this.patientId = patientId;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public static UpdateAdditionalInfoCommand fromRequest(UUID id, UpdateAdditionalInfoRequest request) {
        return new UpdateAdditionalInfoCommand(id, request.getPatientId(), request.getMaritalStatus(), request.getOccupation(),
                request.getEmergencyContactName(), request.getEmergencyContactPhone());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateAdditionalInfoMessage();
    }
}
