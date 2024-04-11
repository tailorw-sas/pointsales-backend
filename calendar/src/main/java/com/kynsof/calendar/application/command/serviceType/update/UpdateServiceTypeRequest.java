package com.kynsof.calendar.application.command.serviceType.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateServiceTypeRequest {
    private String name;
    private byte[] image;
}