package com.kynsof.shift.domain.service;

import com.kynsof.shift.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.shift.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;

public interface ImportTurnerSpecialtiesService {


    public void readExcel(ImportTurnerSpecialtiesRequest request);
    public ImportProcessStatusResponse importStatus(String importProcessId);
}
