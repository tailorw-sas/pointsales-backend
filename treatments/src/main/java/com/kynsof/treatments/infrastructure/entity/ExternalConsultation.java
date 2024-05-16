package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(nullable = false)
    private String referenceNumber;

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
    private String medicalSpeciality;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "externalConsultation")
    private List<Diagnosis> diagnoses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "externalConsultation")
    private List<Treatment> treatments;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    private String observations;

    @OneToOne(mappedBy = "externalConsultation", cascade = CascadeType.ALL)
    private ExamOrder examOrder;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public ExternalConsultation(ExternalConsultationDto dto) {
        this.id = dto.getId();
        this.patient = new Patients(dto.getPatient());
        this.consultationReason = dto.getConsultationReason();
        this.medicalHistory = dto.getMedicalHistory();
        this.physicalExam = dto.getPhysicalExam();
        this.consultationTime = dto.getConsultationTime();
        this.doctor = new Doctor(dto.getDoctor());
        this.observations = dto.getObservations();
        this.medicalSpeciality = dto.getMedicalSpeciality();
        this.treatments = dto.getTreatments() != null ? dto.getTreatments().stream().map(t -> {
            Treatment treatment = new Treatment(t);
            treatment.setExternalConsultation(this);
            return treatment;
        }).toList() : new ArrayList<>();
        this.diagnoses = dto.getDiagnoses() != null ? dto.getDiagnoses().stream().map(d -> {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setId(d.getId());
            diagnosis.setExternalConsultation(this);
            diagnosis.setDescription(d.getDescription());
            diagnosis.setIcdCode(d.getIcdCode());
            return diagnosis;
        }).toList() : new ArrayList<>();

        ExamOrder examOrder = new ExamOrder(dto.getExamOrder());
        examOrder.setExternalConsultation(this);
        this.examOrder = examOrder;
        this.business = new Business(dto.getBusiness());
    }

    public ExternalConsultationDto toAggregate() {
        List<TreatmentDto> treatmentList = this.getTreatments().stream()
                .map(treatment -> new TreatmentDto(treatment.getId(),treatment.getDescription(),
                        treatment.getMedicines().toAggregate(), treatment.getQuantity(), treatment.getMedicineUnit()))
                .toList();

        List<DiagnosisDto> diagnosisDtoList = this.getDiagnoses().stream()
                .map(treatment -> new DiagnosisDto(treatment.getId(),
                        treatment.getIcdCode(), treatment.getDescription()))
                .toList();
        return new ExternalConsultationDto(this.id, this.patient.toAggregate(), this.doctor.toAggregate(),
                this.consultationTime, this.consultationReason, this.medicalHistory, this.physicalExam, diagnosisDtoList,
                treatmentList, this.observations, this.examOrder.toAggregateSimple(), business.toAggregate(),
                medicalSpeciality);
    }

    @PrePersist
    protected void onCreate() {
        consultationTime = new Date();
    }
}
