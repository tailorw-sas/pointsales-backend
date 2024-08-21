package com.kynsof.shift.application.command.tunerSpecialties.importExcel;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class ImportTunerSpecialtiesCommand implements ICommand {

    private final ImportTurnerSpecialtiesRequest request;

    public ImportTunerSpecialtiesCommand(ImportTurnerSpecialtiesRequest request) {
        this.request = request;
    }

    @Override
    public ICommandMessage getMessage() {
        return null;
    }
}
