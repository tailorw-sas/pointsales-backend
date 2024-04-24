package com.kynsof.patients.application.command.medicalInformation.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateMedicalInformationCommand implements ICommand {

    private UUID id;

    private String bloodType;
    private String medicalHistory;
    private UUID patientId;
    private List<CreateAllergyRequest> allergies;
    private List<CreateCurrentMedicationRequest> currentMedications;

    public CreateMedicalInformationCommand(UUID patientId, String bloodType, String medicalHistory,
            List<CreateAllergyRequest> allergies, List<CreateCurrentMedicationRequest> currentMedications) {
        this.patientId = patientId;
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
        this.allergies = allergies;
        this.currentMedications = currentMedications;
    }

    public static CreateMedicalInformationCommand fromRequest(UUID patientId, CreateMedicalInformationRequest request) {
        return new CreateMedicalInformationCommand(patientId, request.getBloodType(), request.getMedicalHistory(),
                request.getAllergies(), request.getCurrentMedications());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateMedicalInformationMessage(id);
    }
}
