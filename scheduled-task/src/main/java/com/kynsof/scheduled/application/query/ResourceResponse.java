package com.kynsof.scheduled.application.query;

import com.kynsof.scheduled.domain.dto.EResourceStatus;
import com.kynsof.scheduled.domain.dto.ResourceDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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