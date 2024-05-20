package com.kynsof.calendar.application.command.businessService.create;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class CreateAllBusinessServicesRequest {
    private UUID idBusiness;
    private Set<CreateBusinessServicesPriceRequest> services;
}
