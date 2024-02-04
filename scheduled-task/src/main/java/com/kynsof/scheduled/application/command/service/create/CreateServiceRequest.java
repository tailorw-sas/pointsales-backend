package com.kynsof.scheduled.application.command.service.create;

import com.kynsof.scheduled.domain.dto.EServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateServiceRequest {

    private EServiceType type;
    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;

}
