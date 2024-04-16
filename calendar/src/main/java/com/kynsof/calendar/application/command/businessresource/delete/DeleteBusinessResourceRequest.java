package com.kynsof.calendar.application.command.businessresource.delete;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBusinessResourceRequest {

    private UUID business;
    private UUID resource;
}
