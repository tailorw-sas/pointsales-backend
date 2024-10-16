package com.kynsof.treatments.infrastructure.entity;

import com.kynsof.treatments.domain.dto.Cie10Dto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cie10 implements Serializable {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(unique = true)
    private String code;

    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Cie10(Cie10Dto cie10Dto) {
        this.id = cie10Dto.getId();
        this.code = cie10Dto.getCode();
        this.name = cie10Dto.getName();
    }

    public Cie10Dto toAggregate() {
        return new Cie10Dto(this.id,this.code, this.name);
    }
}
