package com.kynsof.treatments.application.command.externalConsultation.updateAll;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateExternalConsultationAllRequest {
    private UUID patient;
    private UUID doctor;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String observations;
    private UpdateExamOrderAllRequest examOrder;
    private List<UpdateDiagnosisAllRequest> diagnosis;
    private List<UpdateTreatmentAllRequest> treatments;
}
