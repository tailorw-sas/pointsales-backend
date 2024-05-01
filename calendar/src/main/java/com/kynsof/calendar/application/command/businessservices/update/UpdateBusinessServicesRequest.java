package com.kynsof.calendar.application.command.businessservices.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessServicesRequest {

    private UUID business;
    private UUID service;
}
