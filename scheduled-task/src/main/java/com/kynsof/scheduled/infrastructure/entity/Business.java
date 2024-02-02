package com.kynsof.scheduled.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Lob
    private String description;

    @Lob
    private byte[] logo;

    private String ruc;

    private String status;

    // Relación de muchos a muchos con Resource
    @ManyToMany(mappedBy = "businesses", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Resource> resources = new HashSet<>();

   // @JsonIgnoreProperties("businesses")
    @ManyToMany(mappedBy = "businesses", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Service> services = new HashSet<>();

    // Relación de uno a muchos con Schedule
    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Schedule> schedules = new HashSet<>();

    // Relación de uno a muchos con Receipt
    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Receipt> receipts = new HashSet<>();
}
