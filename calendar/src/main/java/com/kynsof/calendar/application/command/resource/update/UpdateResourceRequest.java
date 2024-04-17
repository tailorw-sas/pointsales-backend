package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResourceRequest {
    private byte[] image;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;
    private String identification;
}