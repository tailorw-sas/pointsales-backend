package com.kynsof.share.core.domain.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servicekafka implements Serializable {
    private UUID id;
    private UUID type;
    private String status;
    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
    private Boolean applyIva;
    private int estimatedDuration;
    private String code;

    private boolean preferFlag;
    private int maxPriorityCount;
    private int priorityCount;
    private int currentLoop;
    private int order;

}
