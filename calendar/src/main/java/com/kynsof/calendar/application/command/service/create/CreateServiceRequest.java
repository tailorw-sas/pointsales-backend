package com.kynsof.calendar.application.command.service.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceRequest {

    private UUID serviceTypeId;
    private byte[] picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;

}
