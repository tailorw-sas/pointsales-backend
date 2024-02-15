package com.kynsof.scheduled.application.query;

import com.kynsof.scheduled.domain.dto.EServiceStatus;
import com.kynsof.scheduled.domain.dto.EServiceType;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServicesResponse implements IResponse {
    private UUID id;
    private EServiceType type;
    private EServiceStatus status;
    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public ServicesResponse(ServiceDto object) {
        this.id = object.getId();
        this.type = object.getType();
        this.status = object.getStatus();
        this.picture = object.getPicture();
        this.name = object.getName();
        this.normalAppointmentPrice = object.getNormalAppointmentPrice();
        this.expressAppointmentPrice = object.getExpressAppointmentPrice();
        this.description = object.getDescription();
        this.createAt = object.getCreateAt();
        this.updateAt = object.getUpdateAt();
        this.deleteAt = object.getDeleteAt();
    }

}