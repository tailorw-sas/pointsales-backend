package com.kynsof.treatments.application.command.externalConsultation.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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
    private List<DiagnosisRequest> diagnoses;
    private List<TreatmentRequest> treatments;
    private String observations;


    public CreateExternalConsultationCommand(UUID patientId, UUID doctorId, String consultationReason,
                                             String medicalHistory, String physicalExam, List<DiagnosisRequest> diagnoses,
                                             List<TreatmentRequest> treatments, String observations){

        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationReason = consultationReason;
        this.medicalHistory = medicalHistory;
        this.physicalExam = physicalExam;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.observations = observations;
    }

    public static CreateExternalConsultationCommand fromRequest(CreateExternalConsultationRequest request) {
        return new CreateExternalConsultationCommand(request.getPatientId(), request.getDoctorId(),
                request.getConsultationReason(), request.getMedicalHistory(), request.getPhysicalExam(),
                request.getDiagnoses(),request.getTreatments(),request.getObservations());
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateExternalConsultationMessage(id);
    }
}
