package com.kynsof.treatments.domain.dto;

import com.kynsof.treatments.infrastructure.entity.Patients;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ExternalConsultationDto {

    private UUID id;

    private PatientDto patient;
    private DoctorDto doctor;
    private Date consultationTime;
    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;
    private List<DiagnosisDto> diagnoses;
    private List<TreatmentDto> treatments;
    private String observations;
}
