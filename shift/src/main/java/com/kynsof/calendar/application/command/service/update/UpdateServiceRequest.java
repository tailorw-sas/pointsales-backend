package com.kynsof.calendar.application.command.service.update;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceRequest {
    private UUID type;
    private String image;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
    private boolean applyIva;
    private EServiceStatus status;
    private Integer estimatedDuration;


    private boolean preferFlag;
    private int maxPriorityCount;
    private int priorityCount;
    private int currentLoop;
    private int order;
}