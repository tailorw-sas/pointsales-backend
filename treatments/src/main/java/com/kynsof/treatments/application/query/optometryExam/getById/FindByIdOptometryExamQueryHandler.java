package com.kynsof.treatments.application.query.optometryExam.getById;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.OptometryExamDto;
import com.kynsof.treatments.domain.service.IOptometryExamService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdOptometryExamQueryHandler implements IQueryHandler<FindByIdOptometryExamQuery, OptometryExamResponse> {

    private final IOptometryExamService serviceImpl;

    public FindByIdOptometryExamQueryHandler(IOptometryExamService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public OptometryExamResponse handle(FindByIdOptometryExamQuery query) {
        OptometryExamDto contactInfoDto = serviceImpl.findById(query.getId());

        return new OptometryExamResponse(contactInfoDto);
    }
}
