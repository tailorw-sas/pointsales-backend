package com.kynsof.treatments.application.command.externalConsultation.createAll;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateExternalConsultationAllRequest {

    private UUID patient;
    private UUID doctor;
    private UUID business;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String observations;
    private CreateExamOrderAllRequest examOrder;
    private List<DiagnosisRequest> diagnosis;
    private List<TreatmentRequest> treatments;
}
