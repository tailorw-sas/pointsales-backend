package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.PlaceDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Size(max = 150)
    @NotBlank
    private String name;

    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private EServiceStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    public Place(PlaceDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.code = dto.getCode();
        this.block = new Block(dto.getBlock());
        this.business = new Business(dto.getBusinessDto());
    }

    public PlaceDto toAggregate() {
        return new PlaceDto(this.id, this.name, status, code, block.toAggregate(), business.toAggregate());
    }
}