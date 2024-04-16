package com.kynsoft.notification.application.query.advertisingcontent.getById;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.notification.domain.dto.*;
import java.time.LocalDateTime;
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
    private String title;
    private String description;
    private ContentType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String url;
    private String link;

    public AdvertisingContentResponse(AdvertisingContentDto advertisingContentDto) {
        this.id = advertisingContentDto.getId();
        this.title = advertisingContentDto.getTitle();
        this.description = advertisingContentDto.getDescription();
        this.type = advertisingContentDto.getType();
        this.createdAt = advertisingContentDto.getCreatedAt();
        this.updatedAt = advertisingContentDto.getUpdatedAt();
        this.url = advertisingContentDto.getUrl();   
        this.link = advertisingContentDto.getLink();
    }

}
