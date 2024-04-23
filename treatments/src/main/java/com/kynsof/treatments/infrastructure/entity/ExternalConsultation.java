package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "externalConsultation")
    private List<Diagnosis> diagnoses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "externalConsultation")
    private List<Treatment> treatments;

    private String observations;

    @OneToMany(mappedBy = "externalConsultation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExamOrder> examOrders;

    public ExternalConsultation(ExternalConsultationDto dto) {
        this.id = dto.getId();
        this.patient = new Patients(dto.getPatient());
        this.consultationReason = dto.getConsultationReason();
        this.medicalHistory = dto.getMedicalHistory();
        this.physicalExam = dto.getPhysicalExam();
        this.consultationTime = dto.getConsultationTime();
        this.doctor = new Doctor(dto.getDoctor());
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
