package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.calendar.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;
import com.kynsof.calendar.domain.excel.TurnerSpecialtiesRow;
import com.kynsof.share.core.application.excel.ExcelBean;

public interface ImportTurnerSpecialtiesService {


    public void readExcel(ImportTurnerSpecialtiesRequest request);
    public ImportProcessStatusResponse importStatus(String importProcessId);
}
