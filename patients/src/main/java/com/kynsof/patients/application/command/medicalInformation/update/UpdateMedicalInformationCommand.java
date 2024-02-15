package com.kynsof.patients.application.command.medicalInformation.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.patients.domain.dto.enumTye.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateMedicalInformationCommand implements ICommand {
    private UUID id;
    private String bloodType;
    private String medicalHistory;
    private Status status;


    public UpdateMedicalInformationCommand(UUID id, String bloodType, String medicalHistory, Status status) {
        this.id = id;
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
        this.status = status;
    }

    public static UpdateMedicalInformationCommand fromRequest(UUID id, UpdateMedicalInformationRequest request) {
        return new UpdateMedicalInformationCommand(id, request.getBloodType(), request.getMedicalHistory(), request.getStatus());
    }


    @Override
    public ICommandMessage getMessage() {
        return new UpdateMedicalInformationMessage();
    }
}
