package com.kynsoft.notification.application.query.jasperreporttemplate.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindJasperReportTemplateByIdQuery implements IQuery {

    private final UUID id;

    public FindJasperReportTemplateByIdQuery(UUID id) {
        this.id = id;
    }

}
