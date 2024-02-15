package com.kynsof.scheduled.application.query;

import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.EBusinessStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BusinessResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private byte[] logo;
    private String ruc;
    private EBusinessStatus status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public BusinessResponse(BusinessDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.description = object.getDescription();
        this.logo = object.getLogo();
        this.ruc = object.getRuc();
        this.status = object.getStatus();
        this.createAt = object.getCreateAt();
        this.updateAt = object.getUpdateAt();
        this.deleteAt = object.getDeleteAt();
    }

    public BusinessResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}