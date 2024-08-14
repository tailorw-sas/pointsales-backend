package com.kynsof.calendar.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resource_service")
@Getter
@Setter
@NoArgsConstructor
public class ResourceService {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @ManyToOne()
    @JoinColumn(name = "service_id")
    private Services service;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
