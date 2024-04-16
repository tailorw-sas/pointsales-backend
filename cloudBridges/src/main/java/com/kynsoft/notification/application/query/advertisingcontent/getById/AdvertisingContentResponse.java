package com.kynsoft.notification.application.query.advertisingcontent.getById;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.notification.domain.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisingContentResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private ContentType type;

    public AdvertisingContentResponse(AdvertisingContentDto advertisingContentDto) {
        this.id = advertisingContentDto.getId();
        this.name = advertisingContentDto.getName();
        this.description = advertisingContentDto.getDescription();
        this.type = advertisingContentDto.getType();
    }

}
