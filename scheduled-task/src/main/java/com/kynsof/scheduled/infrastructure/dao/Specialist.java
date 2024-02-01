package com.kynsof.scheduled.infrastructure.dao;

import com.fasterxml.jackson.annotation.*;
import com.kynsof.scheduled.domain.EServiceType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Specialist {

    @Id
    private UUID id;

    private String picture;

    @Size(max = 250)
    @NotBlank
    private String name;

    @Size(max = 10)
    @NotBlank
    private String registrationNumber;

    @Size(max = 100)
    private String language;

    @Enumerated(EnumType.STRING)
    private EServiceType type;

    //Indica que dicho especialista puede o no manejar citas express
    private Boolean expressAppointments;

    @JsonIgnoreProperties("specialists")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "specialist_service",
            joinColumns = @JoinColumn(name = "specialist_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services = new HashSet<>();

    @JsonIgnoreProperties({"qualifications", "specialists"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "specialist_qualification",
            joinColumns = @JoinColumn(name = "specialist_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<Qualification> qualifications = new HashSet<>();

    public Specialist(String picture, String name, String registrationNumber, String language, EServiceType type, boolean expressAppointments, Set<Service> services) {
        this.picture = picture;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.language = language;
        this.type = type;
        this.expressAppointments = expressAppointments;
        this.services = (services != null) ? services : new HashSet<>();
    }

    public void addService(Service service) {
        this.services.add(service);
        service.getSpecialists().add(this);
    }

    public void removeService(UUID serviceId) {
        Service service = this.services.stream().filter(t -> t.getId().equals(serviceId)).findFirst().orElse(null);
        if (service != null) {
            this.services.remove(service);
            service.getSpecialists().remove(this);
        }
    }

    //Qualifications
    public void addQualification(Qualification qualification) {
        this.qualifications.add(qualification);
        qualification.getSpecialists().add(this);
    }

    public void removeQualification(UUID qualificationId) {
        Qualification qualification = this.qualifications.stream().filter(q -> q.getId().equals(qualificationId)).findFirst().orElse(null);
        if (qualification != null) {
            this.qualifications.remove(qualification);
            qualification.getSpecialists().remove(this);
        }
    }
}
