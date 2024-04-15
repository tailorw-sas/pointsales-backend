package com.kynsof.calendar.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "business_resource")
@Getter
@Setter
@NoArgsConstructor
public class BusinessResource {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
