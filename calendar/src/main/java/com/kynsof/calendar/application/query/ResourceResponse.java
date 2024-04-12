package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ResourceResponse implements IResponse {
    private UUID id;
    private String name;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;
    private List<ServicesResponse> services;

    public ResourceResponse(ResourceDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.registrationNumber = object.getRegistrationNumber();
        this.language = object.getLanguage();
        this.status = object.getStatus();
        this.expressAppointments = object.getExpressAppointments();
        this.services = new ArrayList<>();
        for (ServiceDto service : object.getServices()) {
            this.services.add(new ServicesResponse(service));
        }
    }

    public ResourceResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}