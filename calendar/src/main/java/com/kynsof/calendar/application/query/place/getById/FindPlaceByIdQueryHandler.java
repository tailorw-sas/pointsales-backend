package com.kynsof.calendar.application.query.place.getById;

import com.kynsof.calendar.application.query.BlockResponse;
import com.kynsof.calendar.domain.dto.BlockDto;
import com.kynsof.calendar.domain.service.IBlockService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindPlaceByIdQueryHandler implements IQueryHandler<FindPlaceByIdQuery, BlockResponse>  {

    private final IBlockService service;

    public FindPlaceByIdQueryHandler(IBlockService service) {
        this.service = service;
    }

    @Override
    public BlockResponse handle(FindPlaceByIdQuery query) {
        BlockDto response = service.findById(query.getId());

        return new BlockResponse(response);
    }
}
