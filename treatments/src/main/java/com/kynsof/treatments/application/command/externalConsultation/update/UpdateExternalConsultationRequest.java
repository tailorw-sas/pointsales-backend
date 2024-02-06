package com.kynsof.treatments.application.command.externalConsultation.update;

import com.kynsof.treatments.application.command.externalConsultation.create.DiagnosisRequest;
import com.kynsof.treatments.application.command.externalConsultation.create.TreatmentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateExternalConsultationRequest {
    private UUID patientId;
    private UUID doctorId;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private List<DiagnosisRequest> diagnoses;
    private List<TreatmentRequest> treatments;
    private String observations;
}
