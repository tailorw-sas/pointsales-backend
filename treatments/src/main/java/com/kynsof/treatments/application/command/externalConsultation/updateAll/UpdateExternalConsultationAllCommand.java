package com.kynsof.treatments.application.command.externalConsultation.updateAll;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateExternalConsultationAllCommand implements ICommand {

    private UUID id;
    private UUID patientId;
    private UUID doctorId;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String observations;
    private final UpdateExamOrderAllRequest examOrder;
    private final List<UpdateDiagnosisAllRequest> diagnosis;
    private final List<UpdateTreatmentAllRequest> treatments;

    public UpdateExternalConsultationAllCommand(UUID patientId, UUID doctorId, String consultationReason,
                                                String medicalHistory, String physicalExam, String observations,
                                                UpdateExamOrderAllRequest examOrder,List<UpdateDiagnosisAllRequest> diagnosis,
                                                List<UpdateTreatmentAllRequest> treatments) {

        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationReason = consultationReason;
        this.medicalHistory = medicalHistory;
        this.physicalExam = physicalExam;
        this.observations = observations;
        this.diagnosis = diagnosis;
        this.treatments = treatments;
        this.examOrder = examOrder;
    }

    public static UpdateExternalConsultationAllCommand fromRequest(UpdateExternalConsultationAllRequest request) {
        return new UpdateExternalConsultationAllCommand(request.getPatient(), request.getDoctor(),
                request.getConsultationReason(), request.getMedicalHistory(), request.getPhysicalExam(),
                request.getObservations(), request.getExamOrder(), request.getDiagnosis(), request.getTreatments());
    }

    @Override
    public ICommandMessage getMessage() {
        return new UpdateExternalConsultationAllMessage(id);
    }
}
