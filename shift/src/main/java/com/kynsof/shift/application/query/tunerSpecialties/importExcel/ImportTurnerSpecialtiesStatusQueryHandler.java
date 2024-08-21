package com.kynsof.shift.application.query.tunerSpecialties.importExcel;

import com.kynsof.shift.domain.service.ImportTurnerSpecialtiesService;
import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class ImportTurnerSpecialtiesStatusQueryHandler implements IQueryHandler<ImportTurnerSpecialtiesStatusQuery,ImportProcessStatusResponse> {

    private final ImportTurnerSpecialtiesService turnerSpecialtiesService;

    public ImportTurnerSpecialtiesStatusQueryHandler(ImportTurnerSpecialtiesService turnerSpecialtiesService) {
        this.turnerSpecialtiesService = turnerSpecialtiesService;
    }

    @Override
    public ImportProcessStatusResponse handle(ImportTurnerSpecialtiesStatusQuery query) {
        return turnerSpecialtiesService.importStatus(query.getImportProcessId());
    }
}
