package com.kynsof.calendar.infrastructure.entity;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.EBusinessStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    @Lob
    private byte[] logo;

    private String ruc;

    @Enumerated(EnumType.STRING)
    private EBusinessStatus status;

    @Column(nullable = true)
    private LocalDateTime createAt;

    @Column(nullable = true)
    private LocalDateTime updateAt;

    @Column(nullable = true)
    private LocalDateTime deleteAt;

    // Relación de muchos a muchos con Resource
    @ManyToMany(mappedBy = "businesses", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Resource> resources = new HashSet<>();

   // @JsonIgnoreProperties("businesses")
    @ManyToMany(mappedBy = "businesses", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Services> services = new HashSet<>();

    public Business(BusinessDto business) {
        this.id = business.getId();
        this.name = business.getName();
        this.description = business.getDescription();
        this.logo = business.getLogo();
        this.ruc = business.getRuc();
        this.status = business.getStatus();
    }

    public BusinessDto toAggregate () {
        return new BusinessDto(id, name, description, logo, ruc, status, createAt, updateAt, deleteAt);
    }
}
