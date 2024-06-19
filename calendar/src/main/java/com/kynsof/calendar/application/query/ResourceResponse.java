package com.kynsof.calendar.application.query;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ResourceResponse implements IResponse {
    private UUID id;
    private String name;
    private String image;


    public ResourceResponse(ResourceDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.image = object.getImage();
    }

    public ResourceResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}