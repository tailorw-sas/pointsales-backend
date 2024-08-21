package com.kynsof.calendar.application.command.tunerSpecialties.importExcel;

import com.kynsof.calendar.domain.excel.TurnerSpecialtiesRow;
import com.kynsof.calendar.domain.service.ImportTurnerSpecialtiesService;
import com.kynsof.share.core.application.excel.ExcelBean;
import com.kynsof.share.core.application.excel.ReaderConfiguration;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.infrastructure.excel.ExcelBeanReader;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class ImportTunerSpecialtiesCommandHandler implements ICommandHandler<ImportTunerSpecialtiesCommand> {

    private final ImportTurnerSpecialtiesService importTurnerSpecialtiesService;

    public ImportTunerSpecialtiesCommandHandler(ImportTurnerSpecialtiesService importTurnerSpecialtiesService) {
        this.importTurnerSpecialtiesService = importTurnerSpecialtiesService;
    }

    @Override
    public void handle(ImportTunerSpecialtiesCommand command) {
        importTurnerSpecialtiesService.readExcel(command.getRequest());
    }
}


