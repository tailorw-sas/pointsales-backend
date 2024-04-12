package com.kynsof.calendar.application.command.resource.addServices;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AddServiceRequest {

    private List<UUID> serviceIds;

}
