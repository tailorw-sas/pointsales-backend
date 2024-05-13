package com.kynsof.calendar.application.command.businessService.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CreateAllBusinessServicesCommandHandler implements ICommandHandler<CreateAllBusinessServicesCommand> {

    private final IBusinessServicesService businessServicesService;
    private final IBusinessService businessService;
    private final IServiceService serviceService;
    public CreateAllBusinessServicesCommandHandler(IBusinessServicesService service, IBusinessService serviceBusiness, IServiceService serviceService) {
        this.businessServicesService = service;
        this.businessService = serviceBusiness;
        this.serviceService = serviceService;
    }

    @Override
    public void handle(CreateAllBusinessServicesCommand command) {
        BusinessDto _business = businessService.findById(command.getIdBusiness());
        List<BusinessServicesDto> _businessServicePrice = command.getServices().stream().map(service-> {
            ServiceDto _service = serviceService.findByIds(service.getService());
            BusinessServicesDto dto = new BusinessServicesDto();
            dto.setId(UUID.randomUUID());
            dto.setService(_service);
            dto.setBusiness(_business);
            dto.setPrice(service.getPrice());
            return dto;
        }).toList();
     businessServicesService.createAll(_businessServicePrice);
    }
}
