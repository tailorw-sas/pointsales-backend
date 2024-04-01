package com.kynsof.identity.application.query.module.getbyid;

import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ModuleResponse implements IResponse {
    private UUID id;
    private String name;
    private String description;
    private byte [] image;

    public ModuleResponse(ModuleDto object) {
        this.id = object.getId();
        this.name = object.getName();
        this.description = object.getDescription();
        this.image = object.getImage();
    }

    public ModuleResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}