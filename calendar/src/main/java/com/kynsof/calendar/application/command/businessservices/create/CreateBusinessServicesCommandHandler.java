package com.kynsof.calendar.application.command.businessservices.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.stereotype.Component;

@Component
public class CreateBusinessServicesCommandHandler implements ICommandHandler<CreateBusinessServicesCommand> {

    private final IBusinessServicesService service;
    private final IBusinessService serviceBusiness;
    private final IServiceService serviceService;

    public CreateBusinessServicesCommandHandler(IBusinessServicesService service, IBusinessService serviceBusiness, IServiceService serviceService) {
        this.service = service;
        this.serviceBusiness = serviceBusiness;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(CreateBusinessServicesCommand command) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getBusiness(), "business", "Business ID cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getService(), "service", "Service ID cannot be null."));

        BusinessDto businessDto = this.serviceBusiness.findById(command.getBusiness());
        ServiceDto serviceDto = this.serviceService.findById(command.getService());

        this.service.create(new BusinessServicesDto(command.getId(), 
                businessDto, 
                serviceDto, 
                ConfigureTimeZone.getTimeZone()
        ));
    }
}
