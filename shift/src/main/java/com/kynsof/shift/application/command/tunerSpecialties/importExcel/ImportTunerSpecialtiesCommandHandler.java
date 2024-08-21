package com.kynsof.shift.application.command.tunerSpecialties.importExcel;

import com.kynsof.shift.domain.service.ImportTurnerSpecialtiesService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

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


