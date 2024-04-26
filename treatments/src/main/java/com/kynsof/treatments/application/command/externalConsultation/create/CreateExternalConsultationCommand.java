package com.kynsof.treatments.application.command.externalConsultation.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateExternalConsultationCommand implements ICommand {

    private UUID id;
    private UUID patientId;
    private UUID doctorId;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String observations;
    private UUID examOrder;

    public CreateExternalConsultationCommand(UUID patientId, UUID doctorId, String consultationReason,
            String medicalHistory, String physicalExam, String observations, UUID examOrder) {

        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationReason = consultationReason;
        this.medicalHistory = medicalHistory;
        this.physicalExam = physicalExam;
        this.observations = observations;
        this.examOrder = examOrder;
    }

    public static CreateExternalConsultationCommand fromRequest(CreateExternalConsultationRequest request) {
        return new CreateExternalConsultationCommand(request.getPatient(), request.getDoctor(),
                request.getConsultationReason(), request.getMedicalHistory(), request.getPhysicalExam(),
                request.getObservations(), request.getExamOrder());
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateExternalConsultationMessage(id);
    }
}
