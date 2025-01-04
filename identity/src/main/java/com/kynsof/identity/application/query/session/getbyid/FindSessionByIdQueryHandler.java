package com.kynsof.identity.application.query.session.getbyid;

import com.kynsof.identity.domain.dto.SessionDto;
import com.kynsof.identity.domain.interfaces.service.ISessionService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindSessionByIdQueryHandler implements IQueryHandler<FindSessionByIdQuery, SessionByIdResponse> {

    private final ISessionService service;

    public FindSessionByIdQueryHandler(ISessionService service) {
        this.service = service;
    }

    @Override
    public SessionByIdResponse handle(FindSessionByIdQuery query) {
        SessionDto response = service.findById(query.getId());
        return new SessionByIdResponse(response);
    }
}
