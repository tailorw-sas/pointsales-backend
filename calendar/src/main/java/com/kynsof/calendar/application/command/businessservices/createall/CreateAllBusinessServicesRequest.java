package com.kynsof.calendar.application.command.businessservices.createall;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAllBusinessServicesRequest {
    private UUID idBusiness;
    private List<UUID> services;
}
