package com.kynsof.treatments.application.query.exam.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsof.treatments.domain.dto.ExamDto;
import com.kynsof.treatments.domain.service.IExamService;
import org.springframework.stereotype.Component;

@Component
public class FindByIdExamQueryHandler implements IQueryHandler<FindByIdExamQuery, ExamResponse> {

    private final IExamService serviceImpl;

    public FindByIdExamQueryHandler(IExamService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public ExamResponse handle(FindByIdExamQuery query) {
        ExamDto object = serviceImpl.findById(query.getId());

        return new ExamResponse(object);
    }
}
