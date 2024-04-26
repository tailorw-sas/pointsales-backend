package com.kynsof.treatments.application.command.treatment.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTreatmentCommand implements ICommand {

    private UUID id;
    private String description;
    private String medication;
    private String dose;
    private String frequency;
    private String duration;
    private UUID idExternalConsultation;

    public CreateTreatmentCommand(String description, String medication, String dose, String frequency, String duration, UUID idExternalConsultation) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.medication = medication;
        this.description = description;
        this.dose = dose;
        this.frequency = frequency;
        this.duration = duration;
        this.idExternalConsultation = idExternalConsultation;
    }

    public static CreateTreatmentCommand fromRequest(CreateTreatmentRequest request) {
        return new CreateTreatmentCommand(
                request.getDescription(),
                request.getMedication(),
                request.getDose(),
                request.getFrequency(),
                request.getDuration(),
                request.getExternalConsultation()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateTreatmentMessage(id);
    }
}
