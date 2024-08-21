package com.kynsof.shift.application.query.resource.getServiceByBusinessIdByResourceId;

import com.kynsof.shift.application.query.service.ServicesResponse;
import com.kynsof.shift.domain.dto.ServiceDto;
import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class getServiceByBusinessIdByResourceIdQueryHandler implements IQueryHandler<getServiceByBusinessIdByResourceIdQuery, PaginatedResponse>  {

    private final IResourceService service;

    public getServiceByBusinessIdByResourceIdQueryHandler(IResourceService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(getServiceByBusinessIdByResourceIdQuery query) {
        List<ServiceDto> response = service.getAllServicesByResourceAndBusiness(query.getResourceId(), query.getBusinessId());
        List<ServicesResponse> servicesResponses = response.stream().map(ServicesResponse::new).toList();
        return new PaginatedResponse(servicesResponses, servicesResponses.size(), 0,
                0L, servicesResponses.size(), servicesResponses.size());
    }
}
