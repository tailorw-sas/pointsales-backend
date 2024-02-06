package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ExternalConsultation {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date consultationTime;

    private String consultationReason;
    private String medicalHistory;
    private String physicalExam;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "externalConsultation")
    private List<Diagnosis> diagnoses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "externalConsultation")
    private List<Treatment> treatments;

    private String observations;

    public ExternalConsultation(ExternalConsultationDto dto) {
        this.id = dto.getId();
        this.patient = new Patients(dto.getPatient());
        this.consultationReason = dto.getConsultationReason();
        this.medicalHistory = dto.getMedicalHistory();
        this.physicalExam = dto.getPhysicalExam();
        this.consultationTime = dto.getConsultationTime();
        this.doctor = new Doctor(dto.getDoctor());
        this.treatments = dto.getTreatments().stream()
                .map(treatmentDto -> {
                    Treatment treatment = new Treatment();
                    treatment.setDescription(treatmentDto.getDescription());
                    treatment.setDose(treatmentDto.getDose());
                    treatment.setDuration(treatmentDto.getDuration());
                    treatment.setFrequency(treatmentDto.getFrequency());
                    treatment.setMedication(treatmentDto.getMedication());
                    treatment.setExternalConsultation(this);
                    return treatment;
                })
                .collect(Collectors.toList());

        this.diagnoses = dto.getDiagnoses().stream()
                .map(diagnosisDto -> {
                    Diagnosis diagnosis = new Diagnosis();
                    diagnosis.setDescription(diagnosisDto.getDescription());
                    diagnosis.setIcdCode(diagnosisDto.getIcdCode());
                    diagnosis.setExternalConsultation(this);
                    return diagnosis;
                })
                .collect(Collectors.toList());
        this.observations = dto.getObservations();
    }

    public ExternalConsultationDto toAggregate(){
        List<TreatmentDto> treatmentList = this.getTreatments().stream()
                .map(treatment -> {
                    return new TreatmentDto(treatment.getId(),treatment.getDescription(),
                            treatment.getMedication(), treatment.getDose(), treatment.getFrequency(), treatment.getDuration());
                })
                .toList();

        List<DiagnosisDto> diagnosisDtoList = this.getDiagnoses().stream()
                .map(treatment -> {
                    return new DiagnosisDto(treatment.getId(),
                            treatment.getIcdCode(),treatment.getDescription());
                })
                .toList();
        return  new ExternalConsultationDto(this.id, this.patient.toAggregate(), this.doctor.toAggregate(),
                this.consultationTime, this.consultationReason, this.medicalHistory, this.physicalExam, diagnosisDtoList,
                treatmentList, this.observations);
    }
    @PrePersist
    protected void onCreate() {
        consultationTime = new Date();
    }
}
