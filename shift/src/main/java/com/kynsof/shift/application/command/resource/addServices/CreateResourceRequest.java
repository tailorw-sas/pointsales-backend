package com.kynsof.shift.application.command.resource.addServices;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateResourceRequest {
    private UUID resourceId;
    private UUID businessId;
    private String name;
    private String code;
    private String image;
    private List<UUID> serviceIds;
}
