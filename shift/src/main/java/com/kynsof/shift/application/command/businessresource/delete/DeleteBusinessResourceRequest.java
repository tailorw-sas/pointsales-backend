package com.kynsof.shift.application.command.businessresource.delete;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteBusinessResourceRequest {

    private UUID business;
    private UUID resource;
}
