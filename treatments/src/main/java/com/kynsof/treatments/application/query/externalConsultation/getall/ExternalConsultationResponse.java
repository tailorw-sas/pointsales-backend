package com.kynsof.treatments.application.query.externalConsultation.getall;


import com.kynsof.treatments.domain.bus.query.IResponse;
import com.kynsof.treatments.domain.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ExternalConsultationResponse implements IResponse {
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

    public ExternalConsultationResponse(ExternalConsultationDto dto) {
        this.id = dto.getId();
        this.patient = dto.getPatient();
        this.consultationReason = dto.getConsultationReason();
        this.medicalHistory = dto.getMedicalHistory();
        this.physicalExam = dto.getPhysicalExam();
        this.consultationTime = dto.getConsultationTime();
        this.doctor = dto.getDoctor();
        this.treatments = dto.getTreatments();
        this.diagnoses = dto.getDiagnoses();
        this.observations = dto.getObservations();
    }

}