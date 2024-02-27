package com.kynsof.calendar.application.command.service.create;

import com.kynsof.calendar.domain.dto.enumType.EServiceType;
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
