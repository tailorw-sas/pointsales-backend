package com.kynsof.identity.application.query.module.buildStructure;

import com.kynsof.identity.domain.dto.moduleDto.ModuleNodeDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildStructureQueryHandler implements IQueryHandler<BuildStructureQuery, ModuleBuildResponse>  {

    private final IModuleService service;

    public BuildStructureQueryHandler(IModuleService service) {
        this.service = service;
    }

    @Override
    public ModuleBuildResponse handle(BuildStructureQuery query) {
        List<ModuleNodeDto> response = service.buildStructure();

        return new ModuleBuildResponse(response);
    }
}
