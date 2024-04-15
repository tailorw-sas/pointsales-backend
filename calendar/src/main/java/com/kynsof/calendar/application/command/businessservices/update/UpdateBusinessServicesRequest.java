package com.kynsof.calendar.application.command.businessservices.update;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBusinessServicesRequest {

    private UUID business;
    private UUID service;
}
