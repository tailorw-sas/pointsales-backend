package com.kynsof.calendar.application.command.businessservices.createall;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllBusinessServicesRequest {
    private UUID idBusiness;
    private List<UUID> services;
}
