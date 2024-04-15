package com.kynsof.calendar.application.command.businessservices.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessServicesRequest {

    private UUID business;
    private UUID service;
}
