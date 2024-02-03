package com.kynsof.patients.application.command.medicalInformation.update;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateMedicalInformationCommand implements ICommand {
    private UUID id;
    private UUID patientId;

    private String maritalStatus;

    private String occupation;

    private String emergencyContactName;

    private String emergencyContactPhone;


    public UpdateMedicalInformationCommand(UUID id, UUID patientId, String maritalStatus, String occupation,
                                           String emergencyContactName, String emergencyContactPhone) {
        this.id = id;
        this.patientId = patientId;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public static UpdateMedicalInformationCommand fromRequest(UUID id, UpdateMedicalInformationRequest request) {
        return new UpdateMedicalInformationCommand(id, request.getPatientId(), request.getMaritalStatus(), request.getOccupation(),
                request.getEmergencyContactName(), request.getEmergencyContactPhone());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateMedicalInformationMessage();
    }
}
