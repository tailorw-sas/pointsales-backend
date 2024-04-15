package com.kynsof.calendar.application.command.businessresource.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessresourceRequest {

    private UUID business;
    private UUID resource;
}
