package com.kynsof.calendar.application.command.serviceType.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateServiceTypeRequest {
    private UUID id;
    private String name;
}