package com.kynsof.calendar.application.command.businessresource.update;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessResourceCommandHandler implements ICommandHandler<UpdateBusinessResourceCommand> {

    private final IBusinessResourceService service;
    private final IBusinessService serviceBusiness;
    private final IResourceService serviceResource;

    public UpdateBusinessResourceCommandHandler(IBusinessResourceService service, IBusinessService serviceBusiness, IResourceService serviceResource) {
        this.service = service;
        this.serviceBusiness = serviceBusiness;
        this.serviceResource = serviceResource;
    }

    @Override
    public void handle(UpdateBusinessResourceCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getId(), "id", "BusinessResource ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusiness(), "business", "Business ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getResource(), "resource", "Resource ID cannot be null."));

        BusinessResourceDto update = this.service.findById(command.getId());
        BusinessDto businessDto = this.serviceBusiness.findById(command.getBusiness());
        ResourceDto resourceDto = this.serviceResource.findById(command.getResource());

        update.setBusiness(businessDto);
        update.setResource(resourceDto);

        this.service.update(update);
    }
}
