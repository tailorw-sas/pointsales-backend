package com.kynsof.identity.application.query.module.buildStructure;

import com.kynsof.identity.domain.dto.moduleDto.ModuleNodeDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ModuleBuildResponse implements IResponse {
    private final List<ModuleNodeDto> data;

    public ModuleBuildResponse(List<ModuleNodeDto> moduleNodeDtos) {
        this.data = moduleNodeDtos;

    }

}