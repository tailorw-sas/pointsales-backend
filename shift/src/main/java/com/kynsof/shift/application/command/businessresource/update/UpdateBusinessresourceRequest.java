package com.kynsof.shift.application.command.businessresource.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateBusinessresourceRequest {

    private UUID business;
    private UUID resource;
}
