package com.kynsof.scheduled.application.command.resource.update;

import com.kynsof.scheduled.domain.dto.EResourceStatus;
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