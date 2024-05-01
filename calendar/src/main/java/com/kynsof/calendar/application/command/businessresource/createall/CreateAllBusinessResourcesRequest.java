package com.kynsof.calendar.application.command.businessresource.createall;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAllBusinessResourcesRequest {
    private UUID idBusiness;
    private List<UUID> resources;
}
