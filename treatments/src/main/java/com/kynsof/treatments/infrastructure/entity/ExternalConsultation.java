package com.kynsof.treatments.infrastructure.entity;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "externalConsultation")
    private List<Diagnosis> diagnoses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "externalConsultation")
    private List<Treatment> treatments;

    private String observations;
}
