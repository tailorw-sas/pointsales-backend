package com.kynsof.calendar.application.command.businessservices.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessServicesRequest {

    private UUID business;
    private UUID service;
}
