package com.kynsof.shift.application.command.businessresource.create;

import com.kynsof.shift.domain.dto.BusinessDto;
import com.kynsof.shift.domain.dto.BusinessResourceDto;
import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.shift.domain.rules.businessresource.BusinessResourceMustBeUniqueRule;
import com.kynsof.shift.domain.service.IBusinessResourceService;
import com.kynsof.shift.domain.service.IBusinessService;
import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessResourceCommandHandler implements ICommandHandler<CreateBusinessResourceCommand> {

    private final IBusinessResourceService service;
    private final IBusinessService serviceBusiness;
    private final IResourceService serviceResource;

    public CreateBusinessResourceCommandHandler(IBusinessResourceService service, IBusinessService serviceBusiness, IResourceService serviceResource) {
        this.service = service;
        this.serviceBusiness = serviceBusiness;
        this.serviceResource = serviceResource;
    }

    @Override
    public void handle(CreateBusinessResourceCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusiness(), "business", "Business ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getResource(), "resource", "Resource ID cannot be null."));

        RulesChecker.checkRule(new BusinessResourceMustBeUniqueRule(this.service, command.getBusiness(), command.getResource()));

        BusinessDto businessDto = this.serviceBusiness.findById(command.getBusiness());
        ResourceDto serviceDto = this.serviceResource.findById(command.getResource());

        this.service.create(new BusinessResourceDto(
                command.getId(), 
                businessDto, 
                serviceDto, 
                ConfigureTimeZone.getTimeZone()
        ));
    }
}
