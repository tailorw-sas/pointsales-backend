package com.kynsof.scheduled.infrastructure.dao;

import com.fasterxml.jackson.annotation.*;
import com.kynsof.scheduled.domain.EServiceType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EServiceType type;

    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;

    @Size(max = 2000)
    private String description;

    @JsonIgnoreProperties("services")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "services")
    private Set<Specialist> specialists = new HashSet<>();

    @JsonIgnoreProperties("service")
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Section> sections = new HashSet<>();

    public Service(EServiceType type, String picture, String name, Double normalPrice, Double expressPrice, String description, Set<Specialist> specialists) {
        this.type = type;
        this.picture = picture;
        this.name = name;
        this.normalAppointmentPrice = normalPrice;
        this.expressAppointmentPrice = expressPrice;
        this.description = description;
        this.specialists = (specialists != null) ? specialists : new HashSet<>();
    }

    public void addSpecialist(Specialist specialist) {
        this.specialists.add(specialist);
        specialist.getServices().add(this);
    }

    public void removeSpecialist(UUID specialistId) {
        Specialist specialist = this.specialists.stream().filter(t -> t.getId().equals(specialistId)).findFirst().orElse(null);
        if (specialist != null) {
            this.specialists.remove(specialist);
            specialist.getServices().remove(this);
        }
    }
}