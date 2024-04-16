package com.kynsof.calendar.application.command.businessresource.createall;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAllBusinessResourcesRequest {
    private UUID idBusiness;
    private List<UUID> resources;
}
