package com.kynsof.calendar.application.command.resource.addServices;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AddServiceRequest {
    private UUID resourceId;
    private String name;
    private String image;
    private List<UUID> serviceIds;

}
