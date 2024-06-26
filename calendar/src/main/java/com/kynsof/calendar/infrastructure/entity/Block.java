package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.BlockDto;
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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "block")
public class Block {
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

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Place> places = new HashSet<>();

    public Block(BlockDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.code = dto.getCode();
    }

    public BlockDto toAggregate() {
        return new BlockDto(this.id, this.name, status, code);
    }
}