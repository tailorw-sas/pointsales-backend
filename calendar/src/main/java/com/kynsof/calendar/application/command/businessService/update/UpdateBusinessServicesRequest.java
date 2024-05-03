package com.kynsof.calendar.application.command.businessService.update;

import com.kynsof.calendar.application.command.businessService.create.CreateBusinessServicesPriceRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessServicesRequest {

    private UUID idBusiness;
    private List<CreateBusinessServicesPriceRequest> services;
}
