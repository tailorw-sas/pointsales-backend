package com.kynsof.scheduled.application.query.qualification.getbyid;

import com.kynsof.scheduled.application.query.QualificationResponse;
import com.kynsof.scheduled.domain.dto.QualificationDto;
import com.kynsof.scheduled.domain.service.IQualificationService;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class FindQualificationByIdQueryHandler implements IQueryHandler<FindQualificationByIdQuery, QualificationResponse>  {

    private final IQualificationService service;

    public FindQualificationByIdQueryHandler(IQualificationService service) {
        this.service = service;
    }

    @Override
    public QualificationResponse handle(FindQualificationByIdQuery query) {
        QualificationDto response = service.findById(query.getId());

        return new QualificationResponse(response);
    }
}
