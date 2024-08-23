package com.kynsof.shift.domain.service;

import com.kynsof.shift.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.shift.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;

public interface ImportTurnerSpecialtiesService {


    public void excelProcessor(ImportTurnerSpecialtiesRequest request);
    public ImportProcessStatusResponse turnerSpecialtiesImportStatus(String importProcessId);
}
