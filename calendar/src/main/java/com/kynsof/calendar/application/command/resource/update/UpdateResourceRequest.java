package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.EResourceStatus;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResourceRequest {
    private UUID id;
    private String picture;
    private String name;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;
}