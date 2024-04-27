package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.MedicineUnit;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTreatmentCommand implements ICommand {

    private UUID id;
    private String description;
    private String medication;
    private String quantity;
    private MedicineUnit medicineUnit;
    private UUID idExternalConsultation;

    public CreateTreatmentCommand(String description, String medication, String quantity, MedicineUnit duration, UUID idExternalConsultation) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.medication = medication;
        this.description = description;
        this.quantity = quantity;
        this.medicineUnit = duration;
        this.idExternalConsultation = idExternalConsultation;
    }

    public static CreateTreatmentCommand fromRequest(CreateTreatmentRequest request) {
        return new CreateTreatmentCommand(
                request.getDescription(),
                request.getMedication(),
                request.getQuantity(),
                request.getDuration(),
                request.getExternalConsultation()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateTreatmentMessage(id);
    }
}
