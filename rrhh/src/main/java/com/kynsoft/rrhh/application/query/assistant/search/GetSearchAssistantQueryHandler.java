package com.kynsoft.rrhh.application.query.assistant.search;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import org.springframework.stereotype.Component;

@Component
public class GetSearchAssistantQueryHandler implements IQueryHandler<GetSearchAssistantQuery, PaginatedResponse>{
    private final IAssistantService service;

    public GetSearchAssistantQueryHandler(IAssistantService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(GetSearchAssistantQuery query) {

        return this.service.search(query.getPageable(),query.getFilter());
    }
}
