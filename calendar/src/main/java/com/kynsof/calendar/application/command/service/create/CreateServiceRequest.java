package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceRequest {

    private UUID type;
    private String image;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
    private boolean applyIva;
    private EServiceStatus status;
}
