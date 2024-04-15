package com.kynsof.calendar.application.command.businessresource.update;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBusinessresourceRequest {

    private UUID business;
    private UUID resource;
}
