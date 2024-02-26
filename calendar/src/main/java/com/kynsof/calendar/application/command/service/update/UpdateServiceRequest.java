package com.kynsof.calendar.application.command.service.update;

import com.kynsof.calendar.domain.dto.EServiceType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceRequest {
    private UUID id;
    private EServiceType type;
    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
}