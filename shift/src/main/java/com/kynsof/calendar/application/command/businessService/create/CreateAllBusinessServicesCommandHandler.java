package com.kynsof.calendar.application.command.businessService.create;

import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import org.springframework.stereotype.Component;

import java.util.*;

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
        List<BusinessServicesDto> _businessServicePrice = new ArrayList<>();
        Set<UUID> processedServices = new HashSet<>(); // Conjunto para almacenar servicios procesados

        for (CreateBusinessServicesPriceRequest service : command.getServices()) {
            ServiceDto _service = serviceService.findByIds(service.getService());

            if (!processedServices.contains(_service.getId())) {
                Long count = this.businessServicesService.countRelationsBetweenServiceAndBusiness(_service.getId(), _business.getId());
                if (count == 0) {
                    BusinessServicesDto dto = new BusinessServicesDto();
                    dto.setId(UUID.randomUUID());
                    dto.setService(_service);
                    dto.setBusiness(_business);
                    dto.setPrice(service.getPrice());

                    _businessServicePrice.add(dto);
                    processedServices.add(_service.getId());
                } else {
                    throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND,
                            new ErrorField("id", "BusinessService the relationship already exists.")));
                }
            }
        }
        businessServicesService.createAll(_businessServicePrice);
    }
}
