package com.kynsof.treatments.application.command.treatment.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTreatmentCommand implements ICommand {

    private UUID id;
    private String description;
    private String medication;
    private String quantity;
    private String duration;
    private UUID idExternalConsultation;

    public UpdateTreatmentCommand(UUID id, String description, String medication, String quantity, String duration, UUID idExternalConsultation) {
        this.id = id;
        this.description = description;
        this.medication = medication;
        this.description = description;
        this.quantity = quantity;
        this.duration = duration;
        this.idExternalConsultation = idExternalConsultation;
    }

    public static UpdateTreatmentCommand fromRequest(UpdateTreatmentRequest request, UUID id) {
        return new UpdateTreatmentCommand(
                id, 
                request.getDescription(), 
                request.getMedication(), 
                request.getQuantity(),  
                request.getDuration(),
                request.getIdExternalConsultation()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateTreatmentMessage(id);
    }
}
