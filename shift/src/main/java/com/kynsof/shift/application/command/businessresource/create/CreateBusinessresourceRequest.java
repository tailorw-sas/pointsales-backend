package com.kynsof.shift.application.command.businessresource.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessresourceRequest {

    private UUID business;
    private UUID resource;
}
