package com.kynsof.treatments.application.command.externalConsultation.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateExternalConsultationRequest {

    private UUID patient;
    private UUID doctor;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private String observations;
}
