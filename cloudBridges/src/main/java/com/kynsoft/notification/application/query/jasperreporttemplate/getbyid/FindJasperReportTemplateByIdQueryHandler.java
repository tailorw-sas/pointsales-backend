package com.kynsoft.notification.application.query.jasperreporttemplate.getbyid;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.notification.domain.dto.JasperReportTemplateDto;
import com.kynsoft.notification.domain.service.IJasperReportTemplateService;
import org.springframework.stereotype.Component;

@Component
public class FindJasperReportTemplateByIdQueryHandler implements IQueryHandler<FindJasperReportTemplateByIdQuery, JasperReportTemplateResponse>  {

    private final IJasperReportTemplateService serviceImpl;

    public FindJasperReportTemplateByIdQueryHandler(IJasperReportTemplateService serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @Override
    public JasperReportTemplateResponse handle(FindJasperReportTemplateByIdQuery query) {
        JasperReportTemplateDto jasperReportTemplateDto = serviceImpl.findById(query.getId());
        return new JasperReportTemplateResponse(jasperReportTemplateDto);
    }
}
