package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateExternalConsultationCommand implements ICommand {

    private UUID id;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String observations;

    public UpdateExternalConsultationCommand(UUID id, String consultationReason,
            String medicalHistory, String physicalExam, String observations) {
        this.id = id;
        this.consultationReason = consultationReason;
        this.medicalHistory = medicalHistory;
        this.physicalExam = physicalExam;
        this.observations = observations;
    }

    public static UpdateExternalConsultationCommand fromRequest(UUID id, UpdateExternalConsultationRequest request) {
        return new UpdateExternalConsultationCommand(id, request.getConsultationReason(), request.getMedicalHistory(), request.getPhysicalExam(),
                request.getObservations());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateExternalConsultationMessage();
    }
}
