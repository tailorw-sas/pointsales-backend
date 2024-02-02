package com.kynsof.scheduled.application.query.qualification.getbyid;

import com.kynsof.scheduled.application.query.QualificationResponse;
import com.kynsof.scheduled.domain.dto.QualificationDto;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQueryHandler;
import com.kynsof.scheduled.infrastructure.service.QualificationServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class FindQualificationByIdQueryHandler implements IQueryHandler<FindQualificationByIdQuery, QualificationResponse>  {

    private final QualificationServiceImpl serviceImpl;

    public FindQualificationByIdQueryHandler(QualificationServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public QualificationResponse handle(FindQualificationByIdQuery query) {
        QualificationDto response = serviceImpl.findById(query.getId());

        return new QualificationResponse(response);
    }
}
