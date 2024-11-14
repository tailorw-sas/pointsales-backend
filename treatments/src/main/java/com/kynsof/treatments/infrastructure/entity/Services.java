package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.ServiceDto;
import com.kynsof.treatments.domain.dto.enumDto.EServiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Services {
    @Id
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "service_type_id") // Asume una columna foreign key service_type_id
    private ServiceType type;

    @Enumerated(EnumType.STRING)
    private EServiceStatus status;

    @Column(unique = true)
    private String code;
    private String picture;
    private String name;
    @Column(unique = true)
    private String externalCode;

    @Size(max = 2000)
    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //Only turn
    @Column(nullable = false, name = "prefer_flag")
    private boolean preferFlag;
    @Column(nullable = false, name = "max_priority_count")
    private int maxPriorityCount = 0;
    @Column(nullable = false, name = "priority_count")
    private int priorityCount = 0;
    @Column(nullable = false, name = "current_loop")
    private int currentLoop = 0;
    @Column(nullable = false, name = "order_priority")
    private int order = 0;

    public Services(ServiceDto object) {
        this.id = object.getId();
        this.type = new ServiceType(object.getType());
        this.status = object.getStatus();
        this.picture = object.getPicture();
        this.name = object.getName();
        this.description = object.getDescription();
        this.code = object.getCode();
    }

    public ServiceDto toAggregate () {
        return new ServiceDto(id, type.toAggregate(), status, picture, name,
                description,  code);
    }

}
