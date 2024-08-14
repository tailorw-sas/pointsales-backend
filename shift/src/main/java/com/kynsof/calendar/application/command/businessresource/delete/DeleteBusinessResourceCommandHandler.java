package com.kynsof.calendar.application.command.businessresource.delete;

import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import org.springframework.stereotype.Component;

@Component
public class DeleteBusinessResourceCommandHandler implements ICommandHandler<DeleteBusinessResourceCommand> {

    private final IBusinessResourceService service;
    private final IBusinessService serviceBusiness;
    private final IResourceService serviceResource;

    public DeleteBusinessResourceCommandHandler(IBusinessResourceService service, IBusinessService serviceBusiness, IResourceService serviceResource) {
        this.service = service;
        this.serviceBusiness = serviceBusiness;
        this.serviceResource = serviceResource;
    }

    @Override
    public void handle(DeleteBusinessResourceCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusiness(), "business", "Business ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getResource(), "resource", "Resource ID cannot be null."));

//        BusinessDto businessDto = this.serviceBusiness.findById(command.getBusiness());
//        ResourceDto resourceDto = this.serviceResource.findById(command.getResource());

        BusinessResourceDto delete = this.service.findBusinessResourceByBusinessIdAndResourceId(command.getBusiness(), command.getResource());
        this.service.delete(delete);
    }
}
