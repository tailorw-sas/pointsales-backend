package com.kynsof.shift.application.query.block.getbyid;

import com.kynsof.shift.application.query.BlockResponse;
import com.kynsof.shift.domain.dto.BlockDto;
import com.kynsof.shift.domain.service.IBlockService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindBlockByIdQueryHandler implements IQueryHandler<FindBlockByIdQuery, BlockResponse>  {

    private final IBlockService service;

    public FindBlockByIdQueryHandler(IBlockService service) {
        this.service = service;
    }

    @Override
    public BlockResponse handle(FindBlockByIdQuery query) {
        BlockDto response = service.findById(query.getId());

        return new BlockResponse(response);
    }
}
