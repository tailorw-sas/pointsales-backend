package com.kynsof.calendar.application.query.turn.getbyid;

import com.kynsof.calendar.application.query.TurnResponse;
import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.service.ITurnService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindTurnByIdQueryHandler implements IQueryHandler<FindTurnByIdQuery, TurnResponse>  {

    private final ITurnService service;

    public FindTurnByIdQueryHandler(ITurnService service) {
        this.service = service;
    }

    @Override
    public TurnResponse handle(FindTurnByIdQuery query) {
        TurnDto response = service.findById(query.getId());

        return new TurnResponse(response);
    }
}
