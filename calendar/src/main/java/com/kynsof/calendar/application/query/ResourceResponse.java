package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ResourceResponse implements IResponse {
    private UUID id;
    private String name;
    private String picture;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public ResourceResponse(ResourceDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.picture = object.getPicture();
        this.registrationNumber = object.getRegistrationNumber();
        this.language = object.getLanguage();
        this.status = object.getStatus();
        this.expressAppointments = object.getExpressAppointments();
        this.createAt = object.getCreateAt();
        this.updateAt = object.getUpdateAt();
        this.deleteAt = object.getDeleteAt();
    }

    public ResourceResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}