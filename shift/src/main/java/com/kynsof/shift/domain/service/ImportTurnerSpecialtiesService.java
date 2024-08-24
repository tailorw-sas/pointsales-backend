package com.kynsof.shift.domain.service;

import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.shift.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.shift.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;
import org.springframework.data.domain.Pageable;

public interface ImportTurnerSpecialtiesService {

    public void excelProcessor(ImportTurnerSpecialtiesRequest request);
    public ImportProcessStatusResponse turnerSpecialtiesImportStatus(String importProcessId);

    public PaginatedResponse getTurnerSpecialtiesImportError(String importProcessId, Pageable pageable);
}
