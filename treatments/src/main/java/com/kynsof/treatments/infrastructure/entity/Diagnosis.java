package com.kynsof.treatments.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String icdCode; // Código CIE-10
    private String description;

    @ManyToOne
    @JoinColumn(name = "external_consultation_id")
    private ExternalConsultation externalConsultation;
}
