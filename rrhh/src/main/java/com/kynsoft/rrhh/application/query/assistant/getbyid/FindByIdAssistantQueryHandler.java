package com.kynsoft.rrhh.application.query.assistant.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.rrhh.domain.dto.AssistantDto;
import com.kynsoft.rrhh.domain.interfaces.services.IAssistantService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdAssistantQueryHandler implements IQueryHandler<FindByIdAssistantQuery, AssistantResponse>  {

    private final IAssistantService serviceImpl;

    public FindByIdAssistantQueryHandler(IAssistantService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public AssistantResponse handle(FindByIdAssistantQuery query) {
        AssistantDto doctorDto = serviceImpl.findById(query.getId());

        return new AssistantResponse(doctorDto);
    }
}
