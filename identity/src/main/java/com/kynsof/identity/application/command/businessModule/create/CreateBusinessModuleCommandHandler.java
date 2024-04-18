package com.kynsof.identity.application.command.businessModule.create;

import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.BusinessModuleDto;
import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.interfaces.service.IBusinessModuleService;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.domain.rules.businessmodule.BusinessModuleMustBeUniqueRule;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessModuleCommandHandler implements ICommandHandler<CreateBusinessModuleCommand> {

    private final IModuleService serviceModule;
    private final IBusinessService serviceBusiness;
    private final IBusinessModuleService serviceBusinessModule;

    public CreateBusinessModuleCommandHandler(IModuleService serviceModule, IBusinessService serviceBusiness, IBusinessModuleService serviceBusinessModule) {
        this.serviceModule = serviceModule;
        this.serviceBusiness = serviceBusiness;
        this.serviceBusinessModule = serviceBusinessModule;
    }

    @Override
    public void handle(CreateBusinessModuleCommand command) {
        BusinessDto businessDto = this.serviceBusiness.findById(command.getIdBusiness());

        List<BusinessModuleDto> businessModuleDtos = new ArrayList<>();
        for (UUID idModule : command.getModules()) {
            UUID id = UUID.randomUUID();
            RulesChecker.checkRule(new BusinessModuleMustBeUniqueRule(this.serviceBusinessModule, command.getIdBusiness(), idModule));
            ModuleDto module = this.serviceModule.findById(idModule);
            businessModuleDtos.add(new BusinessModuleDto(id, businessDto, module));
        }

        this.serviceBusinessModule.create(businessModuleDtos);
    }
}
