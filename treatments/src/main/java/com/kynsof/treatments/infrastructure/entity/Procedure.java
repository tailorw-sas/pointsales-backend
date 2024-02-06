package com.kynsof.treatments.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Procedure {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String code;
    private String name;
    private String description;
    private String type;
    private Double price;
}
