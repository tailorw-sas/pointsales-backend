package com.kynsof.identity.infrastructure.identity;

import com.kynsof.identity.domain.dto.GeographicLocationDto;
import com.kynsof.identity.domain.dto.enumType.GeographicLocationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    private GeographicLocationType type;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_pk_geographic_location", nullable = true)
    private GeographicLocation parent;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public GeographicLocation() {
    }

    public GeographicLocation(GeographicLocationDto geographiclocation) {
        this.id = geographiclocation.getId();
        this.name = geographiclocation.getName();
        this.type = geographiclocation.getType();
        this.parent = geographiclocation.getParent() != null
                ? new GeographicLocation(geographiclocation.getParent())
                : null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public GeographicLocationDto toAggregate() {
        GeographicLocationDto parentDto = null;
        if (this.parent != null) {
            parentDto = new GeographicLocationDto(this.parent.getId(), this.parent.getName(), this.parent.getType(), null);
        }
        return new GeographicLocationDto(this.id, this.name, this.type, parentDto);
    }
}
