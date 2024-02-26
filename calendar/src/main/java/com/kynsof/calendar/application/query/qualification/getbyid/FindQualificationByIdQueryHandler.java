package com.kynsof.calendar.application.query.qualification.getbyid;

import com.kynsof.calendar.application.query.QualificationResponse;
import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
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
