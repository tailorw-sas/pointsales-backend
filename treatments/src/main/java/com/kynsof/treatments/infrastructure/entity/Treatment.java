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
public class Treatment {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String description;
    private String medication;
    private String dose;
    private String frequency;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "external_consultation_id")
    private ExternalConsultation externalConsultation;
}
