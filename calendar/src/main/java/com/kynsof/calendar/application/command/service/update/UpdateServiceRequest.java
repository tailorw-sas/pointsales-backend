package com.kynsof.calendar.application.command.service.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceRequest {
    private UUID serviceTypeId;
    private byte[] picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
}