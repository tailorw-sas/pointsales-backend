package com.kynsof.shift.application.query.turn.getbyid;

import com.kynsof.shift.application.query.TurnResponse;
import com.kynsof.shift.domain.dto.TurnDto;
import com.kynsof.shift.domain.service.ITurnService;
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
