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

    public CreateTreatmentCommand(String description, String medication, String dose, String frequency, String duration) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.medication = medication;
        this.description = description;
        this.dose = dose;
        this.frequency = frequency;
        this.duration = duration;
    }

    public static CreateTreatmentCommand fromRequest(CreateTreatmentRequest request) {
        return new CreateTreatmentCommand(request.getDescription(), request.getMedication(), request.getDose(), request.getFrequency(), request.getDuration());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateTreatmentMessage(id);
    }
}