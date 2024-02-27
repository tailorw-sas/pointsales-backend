package com.kynsof.calendar.application.command.resource.create;

import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResourceRequest {

    private String picture;
    private String name;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;

}
