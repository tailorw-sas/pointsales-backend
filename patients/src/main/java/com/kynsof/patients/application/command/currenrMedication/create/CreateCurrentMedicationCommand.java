package com.kynsof.patients.application.command.currenrMedication.create;

import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateCurrentMedicationCommand implements ICommand {
    private UUID id;
    private String dosage;
    private String name;
    private UUID medicalInformationId;


    public CreateCurrentMedicationCommand(UUID medicalInformationId, String code, String name) {
        this.dosage = code;
        this.name = name;
        this.medicalInformationId = medicalInformationId;
    }

    public static CreateCurrentMedicationCommand fromRequest(CreateCurrentMedicationEntityRequest request) {
        return new CreateCurrentMedicationCommand(request.getMedicalInformationId(), request.getDosage(), request.getName());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateCurrentMedicationMessage(id);
    }
}
