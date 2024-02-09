package com.kynsof.patients.infrastructure.entity;

import com.kynsof.patients.domain.dto.enumTye.GeographicLocationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
public class GeographicLocation implements Serializable {

    @Id
    @Column
    private UUID id;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(nullable = false)
    private GeographicLocationType type;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_pk_geographic_location", nullable = true)
    private GeographicLocation parent;

    @Column
    private Long idUbication;

    public GeographicLocation() {
    }

    public GeographicLocation(GeographicLocation geographiclocation) {
        this.id = geographiclocation.getId();
        this.name = geographiclocation.getName();
        this.type = geographiclocation.getType();
        this.parent = geographiclocation.getParent() != null
                ? new GeographicLocation(geographiclocation.getParent())
                : null;
        this.idUbication = geographiclocation.getIdUbication();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
